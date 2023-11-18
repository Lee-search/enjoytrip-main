package com.ssafy.vue.member.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.member.model.MemberDto;

@Mapper
public interface MemberMapper {

	MemberDto login(MemberDto memberDto) throws SQLException;
	MemberDto userInfo(String userId) throws SQLException;
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String userid) throws SQLException;
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
	int idExistingCheck(String userId) throws SQLException;
	void userRegistration(MemberDto memberDto) throws SQLException;
	String userFindId(MemberDto memberDto) throws SQLException;
	String userFindPwd(MemberDto memberDto) throws SQLException;
}
