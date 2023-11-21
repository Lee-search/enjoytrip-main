package com.ssafy.vue.map.model.service;

import com.ssafy.vue.map.model.AttractionDto;
import com.ssafy.vue.map.model.SidoGugunCodeDto;
import com.ssafy.vue.map.model.WhishlistDto;

import java.util.List;
import java.util.Map;

public interface MapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<AttractionDto> getAttractionList(Map<String, String> map) throws Exception;	
	AttractionDto getAttractionDetail(int contentId) throws Exception;
	
	List<WhishlistDto> getWhisList(String user_id) throws Exception;
	void saveWhishlist(WhishlistDto WhishlistDto) throws Exception;
	void deletwhishlist(int contentid) throws Exception;

	
}
