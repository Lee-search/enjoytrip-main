package com.ssafy.vue.board.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel(value = "FileInfoDto : 업로드 파일정보", description = "게시글에 업로드한 파일의 정보를 나타낸다.")
public class FileInfoDto {

	@ApiModelProperty(value = "저장폴더")
	private String saveFolder;
	@ApiModelProperty(value = "원본 파일이름")
	private String originalFile;
	@ApiModelProperty(value = "저장 파일이름")
	private String saveFile;
	
}
