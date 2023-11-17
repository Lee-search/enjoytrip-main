package com.ssafy.vue.spot.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.spot.model.SpotDto;

@Mapper
public interface SpotMapper {
	// 메인 페이지에서 추천 여행지
	List<SpotDto> getHotSpotList(String theme, int sido) throws SQLException;

	// 지역별 조회
	List<SpotDto> getSpotList(Map<String, Integer> map) throws SQLException;

	// 장소 Detail
	SpotDto getSpot(int spotid) throws SQLException;

	List<SpotDto> searchSpot(String keyword) throws SQLException;
	
	// 시도 리스트
	// List<Map<Integer,String>> getSidoList() throws SQLException;
	List<String[]> getSidoList() throws SQLException;

	// 구군 리스트
	List<String[]> getGugunList(int sidoid) throws SQLException;

	int countSpot() throws SQLException;

	SpotDto getThemeTopSpot(String theme) throws SQLException;
	
}
