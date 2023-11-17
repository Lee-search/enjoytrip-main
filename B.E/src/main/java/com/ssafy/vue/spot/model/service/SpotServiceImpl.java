package com.ssafy.vue.spot.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.spot.mapper.SpotMapper;
import com.ssafy.vue.spot.model.SpotDto;

@Service
public class SpotServiceImpl implements SpotService {
	
	private SpotMapper spotMapper;
	
	@Autowired
	public SpotServiceImpl(SpotMapper spotMapper) {
		this.spotMapper = spotMapper;
	}

	@Override
	public List<SpotDto> getHotSpotList(String theme, int sido) throws Exception {
		return null;
	}

	@Override
	public List<SpotDto> getSpotList(Map<String, Integer> map) throws Exception {
		return spotMapper.getSpotList(map);
	}

	@Override
	public SpotDto getSpot(int spotid) throws Exception {
		return spotMapper.getSpot(spotid);
	}

	@Override
	public List<String[]> getSidoList() throws Exception {
		return spotMapper.getSidoList();
	}

	@Override
	public List<String[]> getGugunList(int sidoid) throws Exception {
		return spotMapper.getGugunList(sidoid);
	}

	@Override
	public int countSpot() throws Exception {
		return 0;
	}

	@Override
	public List<SpotDto> searchSpot(String keyword) throws Exception {
		return spotMapper.searchSpot(keyword);
	}

	@Override
	public SpotDto getThemeTopSpot(String theme) throws Exception {
		return spotMapper.getThemeTopSpot(theme);
	}

}
