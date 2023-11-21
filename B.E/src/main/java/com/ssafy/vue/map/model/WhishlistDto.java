package com.ssafy.vue.map.model;




import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class WhishlistDto {

	@ApiModelProperty(value = "콘텐츠번호")
	private String contentid;
	@ApiModelProperty(value = "제목")
	private String title;
	@ApiModelProperty(value = "주소")
	private String addr1;
	@ApiModelProperty(value = "이미지")
	private String firstimage;
	@ApiModelProperty(value = "글쓴이")
	private String user_id;
	
}
