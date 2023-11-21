package com.ssafy.vue.map.controller;

import com.ssafy.vue.map.model.AttractionDto;
import com.ssafy.vue.map.model.SidoGugunCodeDto;
import com.ssafy.vue.map.model.WhishlistDto;
import com.ssafy.vue.map.model.service.MapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/map")
@Api("Map 컨트롤러  API V1")
@Slf4j
public class MapController {
	
	private MapService mapService;

	public MapController(MapService mapService) {
		super();
		this.mapService = mapService;
	}

	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		log.info("sido - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(mapService.getSido(), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "찜목록", notes = "찜목록을 불러온다.", response = List.class)
	@GetMapping("/whishlist")
	public ResponseEntity<List<WhishlistDto>> listWhishlist( @RequestParam("user_id") String user_id) throws Exception {
		log.info("whishlist - 호출");
		
		return new ResponseEntity<List<WhishlistDto>>(mapService.getWhisList(user_id), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "찜목록 저장", notes = "찜목록을 저장한다..")
	@PostMapping("/regist")
	public ResponseEntity<Map<String, Object>> saveWhishlist(
			@RequestBody @ApiParam(value = "게시글 정보 저장.", required = true) WhishlistDto WhishlistDto) throws Exception{
	Map<String, Object> resultMap = new HashMap<>();
	HttpStatus status = HttpStatus.ACCEPTED;
		log.info("whishlist저장 - 호출");
		mapService.saveWhishlist(WhishlistDto);
		status = HttpStatus.CREATED;
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "짐목록삭제", notes = "찜목록을 삭제한다" )
	@DeleteMapping("/{contentid}")
	public ResponseEntity<?> deletwhishlist(@PathVariable("contentid") int contentid) throws Exception {
		log.info("deletwhishlist - 호출");
		mapService.deletwhishlist(contentid);
		return new ResponseEntity<Void>(HttpStatus.OK);

		

	}


	
	
	
	

	@ApiOperation(value = "구군 정보", notes = "시도에 속한 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(
			@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		log.info("gugun - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(mapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@ApiOperation(value = "관광지 목록", notes = "관광지 목록을 리턴합니다.", response = List.class)
	@PostMapping("/list")
	public ResponseEntity<List<AttractionDto>> getAttractionList(@RequestBody Map<String, String> map) throws Exception {
		List<AttractionDto> list = mapService.getAttractionList(map);
		return new ResponseEntity<List<AttractionDto>>(list, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "관광지 상세", notes = "관광지 상세내용을 리턴합니다.", response = AttractionDto.class)
	@GetMapping("/detail")
	public ResponseEntity<Map<String,Object>> getAttractionDetail(@RequestParam("contentId") int contentId) throws Exception {
		log.debug("getAttractionDetail contentId {}", contentId);
		Map<String,Object> result = new HashMap<String, Object>();
		AttractionDto attraction = null;
		String message = null;
		attraction = mapService.getAttractionDetail(contentId);
		result.put("message", message);
		result.put("attraction", attraction);
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}
}
