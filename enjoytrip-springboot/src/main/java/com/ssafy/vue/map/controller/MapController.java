package com.ssafy.vue.map.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.map.model.SidoGugunCodeDto;
import com.ssafy.vue.map.model.service.MapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/map")
@Api("Map 컨트롤러  API V1")
@Slf4j
public class MapController {
	
	private MapService mapService;

	public MapController(MapService mapService) {
		super();
		this.mapService = mapService;
	}

	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		log.info("sido - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(mapService.getSido(), HttpStatus.OK);
	}

	@ApiOperation(value = "구군 정보", notes = "시도에 속한 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(
			@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		log.info("gugun - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(mapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
}
