package com.ssafy.vue.member.model.service;

import com.ssafy.vue.member.model.MemberDto;

import java.lang.reflect.Member;

public interface MemberService {

	MemberDto login(MemberDto memberDto) throws Exception;
	MemberDto userInfo(String userId) throws Exception;
	void saveRefreshToken(String userId, String refreshToken) throws Exception;
	Object getRefreshToken(String userId) throws Exception;
	void deleRefreshToken(String userId) throws Exception;
	int idExistingCheck(String userId) throws Exception; // 아이디 중복 체크
	void userRegistration(MemberDto memberDto) throws Exception;	// 회원 가입
	String userFindId(MemberDto memberDto) throws Exception;
	String userFindPwd(MemberDto memberDto) throws Exception;
    void userModifyInfo(MemberDto memberDto);
}
