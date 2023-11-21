package com.ssafy.vue.map.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.vue.map.model.AttractionDto;
import com.ssafy.vue.map.model.SidoGugunCodeDto;
import com.ssafy.vue.map.model.WhishlistDto;
import com.ssafy.vue.map.model.mapper.MapMapper;

@Service
public class MapServiceImpl implements MapService {
	
	private MapMapper mapMapper;

	public MapServiceImpl(MapMapper mapMapper) {
		super();
		this.mapMapper = mapMapper;
	}

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return mapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return mapMapper.getGugunInSido(sido);
	}

	
	@Override
	public List<AttractionDto> getAttractionList(Map<String, String> map) throws Exception {
		return mapMapper.getAttractionList(map);
	}

	@Override
	public AttractionDto getAttractionDetail(int contentId) throws Exception {
		return mapMapper.getAttractionDetail(contentId);
	}
	
	
	@Override
	public List<WhishlistDto> getWhisList(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return mapMapper.getWhisList(user_id);
	}

	@Override
	public void saveWhishlist(WhishlistDto WhishlistDto) throws Exception {
		mapMapper.saveWhishlist(WhishlistDto);	
	}

	@Override
	public void deletwhishlist(int contentid) throws Exception {
		mapMapper.deletwhishlist(contentid);
		
	}

	
	
	

}
