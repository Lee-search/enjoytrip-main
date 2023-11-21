package com.ssafy.vue.map.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.vue.map.model.AttractionDto;
import com.ssafy.vue.map.model.SidoGugunCodeDto;
import com.ssafy.vue.map.model.WhishlistDto;

public interface MapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<AttractionDto> getAttractionList(Map<String, String> map) throws Exception;	
	AttractionDto getAttractionDetail(int contentId) throws Exception;
	
	List<WhishlistDto> getWhisList(String user_id) throws Exception;
	void saveWhishlist(WhishlistDto WhishlistDto) throws Exception;
	void deletwhishlist(int contentid) throws Exception;

	
}
