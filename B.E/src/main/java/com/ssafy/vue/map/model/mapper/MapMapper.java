package com.ssafy.vue.map.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.vue.map.model.AttractionDto;
import com.ssafy.vue.map.model.SidoGugunCodeDto;
import com.ssafy.vue.map.model.WhishlistDto;

public interface MapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;	
	List<AttractionDto> getAttractionList(Map<String, String> map) throws SQLException;	
	AttractionDto getAttractionDetail(int contentId) throws SQLException;
	
	List<WhishlistDto> getWhisList(String user_id) throws SQLException;
	void saveWhishlist(WhishlistDto WhishlistDto) throws SQLException;	
	void deletwhishlist(int contentid) throws SQLException;

	
}
