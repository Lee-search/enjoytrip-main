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
@ApiModel(value = "CommentDto : 댓글 정보", description = "게시글의 댓글 정보를 나타낸다.")
public class CommentDto {

	@ApiModelProperty(value = "댓글번호")
	private int commentId;
	@ApiModelProperty(value = "글번호")
	private int articleNo;
	@ApiModelProperty(value = "작성자 아이디")
	private String userId;
	@ApiModelProperty(value = "작성자 이름")
	private String userName;
	@ApiModelProperty(value = "댓글 내용")
	private String commentText;
	@ApiModelProperty(value = "작성일")	
	private String commentTime;

}
