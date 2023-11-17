package com.ssafy.vue.spot.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.vue.spot.model.SpotDto;

public interface SpotService {
	// 메인 페이지에서 추천 여행지 - 테마별(전체는 테마 합쳐서)
	List<SpotDto> getHotSpotList(String theme, int sido) throws Exception;

	// 지역별 조회,페이지네이션
	List<SpotDto> getSpotList(Map<String, Integer> map) throws Exception;

	List<SpotDto> searchSpot(String keyword) throws Exception;
	
	// 장소 Detail
	SpotDto getSpot(int spotid) throws Exception;

	// 시도 리스트
	// List<Map<Integer,String>> getSidoList() throws SQLException;
	List<String[]> getSidoList() throws Exception;

	// 구군 리스트
	List<String[]> getGugunList(int sidoid) throws Exception;

	int countSpot() throws Exception;

	SpotDto getThemeTopSpot(String theme) throws Exception;
}
