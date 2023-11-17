package com.ssafy.vue.spot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SpotDto {
	private int spotid;
	private String title;
	private String address;
	private String tel;
	private String image;
	private String hit;
	private String theme;
	private String contentid;
	private String lat;
	private String lang;
	private String sidoid;
	private String gugunid;
	private String desc;
	private String memo;

	
}