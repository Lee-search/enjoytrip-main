package com.ssafy.vue.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ssafy.vue.board.model.BoardDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.vue.member.model.MemberDto;
import com.ssafy.vue.member.model.service.MemberService;
import com.ssafy.vue.util.JWTUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class MemberController {

	private MemberService memberService;
	private JWTUtil jwtUtil;

	public MemberController(MemberService memberService, JWTUtil jwtUtil) {
		super();
		this.memberService = memberService;
		this.jwtUtil = jwtUtil;
	}

	@ApiOperation(value = "로그인", notes = "아이디와 비밀번호를 이용하여 로그인 처리.")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		log.debug("login user : {}", memberDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if(loginUser != null) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getUserId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getUserId());
				log.debug("access token : {}", accessToken);
				log.debug("refresh token : {}", refreshToken);

//				발급받은 refresh token을 DB에 저장.
				memberService.saveRefreshToken(loginUser.getUserId(), refreshToken);

//				JSON으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);

				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}

		} catch (Exception e) {
			log.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId,
			HttpServletRequest request) {
//		logger.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			log.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(userId);
				resultMap.put("userInfo", memberDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(@PathVariable ("userId") @ApiParam(value = "로그아웃할 회원의 아이디.", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("token : {}, memberDto : {}", token, memberDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDto.getUserId()))) {
				String accessToken = jwtUtil.createAccessToken(memberDto.getUserId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("리프레쉬토큰도 사용불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 회원가입 처리 부분 ---------------------------------------------------
	@ApiOperation(value = "아이디체크", notes = "회원가입 전 userid가 존재하는지 체크한다", response = Map.class)
	@GetMapping("/idcheck/{userId}")
	public ResponseEntity<?> idUsableCheck(@PathVariable ("userId") @ApiParam(value = "검색할 회원의 아이디.", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			int cnt = memberService.idExistingCheck(userId);
			log.debug("Count of userId : {}", cnt);
			resultMap.put("count", cnt);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("아이디 조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원가입", notes = "회원가입을 진행한다.", response = Map.class)
	@PostMapping("/registration")
	public ResponseEntity<Map<String, Object>> registerUser(
			@RequestBody @ApiParam(value = "회원가입 시 필요한 개인정보 파라미터", required = true) MemberDto memberDto) {
		log.debug("Registration by : {}", memberDto.toString());
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.userRegistration(memberDto);
			status = HttpStatus.CREATED;
		} 
		
		catch (Exception e) {
			log.debug("회원가입 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 아이디 / 비밀번호 찾기 부분 -------------------------------------------------------------
	@ApiOperation(value = "아이디 찾기", notes = "이름과 이메일을 기준으로 아이디를 찾아서 리턴한다.", response = Map.class)
	@PostMapping("/find/id")
	public ResponseEntity<?> findIdByNameAndEmail(@RequestBody @ApiParam(value = "개인정보 파라미터", required = true) MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
//		System.out.println(memberDto);

		try {
			String userId = memberService.userFindId(memberDto);
			log.debug("userId : {}", userId);
			resultMap.put("userId", userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("아이디 찾기 중 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "비밀번호 찾기", notes = "아이디, 이름과 이메일을 기준으로 비밀번호를 찾아서 리턴한다.", response = Map.class)
	@PostMapping("/find/pwd")
	public ResponseEntity<?> findPwdByIdAndNameAndEmail(@RequestBody @ApiParam(value = "개인정보 파라미터", required = true) MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
//		System.out.println(memberDto);

		try {
			String userPwd = memberService.userFindPwd(memberDto);
			log.debug("userPwd : {}", userPwd);
			resultMap.put("userPwd", userPwd);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("비밀번호 찾기 중 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 개인정보 수정 ------------------------------------------------------------------------------
	@ApiOperation(value = "회원정보 수정", notes = "마이페이지에서 회원정보를 수정한다.", response = Map.class)
	@PutMapping("/modify/info")
	public ResponseEntity<Map<String, Object>> modifyUserInfo(
			@RequestBody @ApiParam(value = "개인정보 파라미터", required = true) MemberDto memberDto) throws Exception {
		log.debug("Modify User Info by : {}", memberDto.toString());

		memberService.userModifyInfo(memberDto);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "비밀번호 변경", notes = "마이페이지에서 비밀번호를 수정한다.", response = Map.class)
	@PostMapping("/modify/pwd")
	public ResponseEntity<Map<String, Object>> modifyUserPwd (
			@RequestBody @ApiParam(value = "개인정보 파라미터", required = true) Map<String, String> map) throws Exception {
		log.debug("Modify User Password by : {}", map.toString());
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try {
			boolean isChanged = memberService.userModifyPwd(map);
			if(isChanged) {
				status = HttpStatus.OK;
			} else { 	// currentPwd 값이 다르면 거절됨
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
			log.debug("비밀번호 찾기 중 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
