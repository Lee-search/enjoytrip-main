package com.ssafy.vue.map.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.vue.map.model.AttractionDto;
import com.ssafy.vue.map.model.SidoGugunCodeDto;

public interface MapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	
	List<AttractionDto> getAttractionList(Map<String, String> map) throws Exception;
	
	AttractionDto getAttractionDetail(int contentId) throws Exception;
	
}
