package com.ssafy.vue.member.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.vue.member.model.MemberDto;
import com.ssafy.vue.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	private MemberMapper memberMapper;

	public MemberServiceImpl(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}

	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		return memberMapper.login(memberDto);
	}
	
	@Override
	public MemberDto userInfo(String userId) throws Exception {
		return memberMapper.userInfo(userId);
	}

	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", refreshToken);
		memberMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return memberMapper.getRefreshToken(userId);
	}

	@Override
	public void deleRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		memberMapper.deleteRefreshToken(map);
	}

	@Override
	public int idExistingCheck(String userId) throws Exception {
		return memberMapper.idExistingCheck(userId);
	}

	@Override
	public void userRegistration(MemberDto memberDto) throws Exception {
		memberMapper.userRegistration(memberDto);
	}

	@Override
	public String userFindId(MemberDto memberDto) throws Exception {
		try {
			return memberMapper.userFindId(memberDto);
		} catch (Exception e) {
			// 결과가 null이 오는 경우, 즉 결과가 없는 경우 빈 문자열 리턴
			e.printStackTrace();
			return "";
		}
	}

	public String userFindPwd(MemberDto memberDto) throws Exception {
		try {
			return memberMapper.userFindPwd(memberDto);
		} catch (Exception e) {
			// 결과가 null이 오는 경우, 즉 결과가 없는 경우 빈 문자열 리턴
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public void userModifyInfo(MemberDto memberDto) {
		memberMapper.userModifyInfo(memberDto);
	}

}
