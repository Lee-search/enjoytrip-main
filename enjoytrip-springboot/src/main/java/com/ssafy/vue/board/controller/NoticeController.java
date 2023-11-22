package com.ssafy.vue.board.controller;

import com.ssafy.vue.board.model.BoardDto;
import com.ssafy.vue.board.model.BoardListDto;
import com.ssafy.vue.board.model.CommentDto;
import com.ssafy.vue.board.model.service.BoardService;
import com.ssafy.vue.board.model.service.NoticeService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/notice")
@Api("공지사항 컨트롤러  API V1")
@Slf4j
public class NoticeController {

	private NoticeService noticeService;

	public NoticeController(NoticeService noticeService) {
		super();
		this.noticeService = noticeService;
	}

	@ApiOperation(value = "게시판 글작성", notes = "새로운 게시글 정보를 입력한다.")
	@PostMapping
	public ResponseEntity<?> writeArticle(
			@RequestBody @ApiParam(value = "게시글 정보.", required = true) BoardDto boardDto) {
		log.info("writeArticle boardDto - {}", boardDto);
		try {
			noticeService.writeArticle(boardDto);
//			return ResponseEntity.ok().build();
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "회원목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping
	public ResponseEntity<?> listArticle(
			@RequestParam @ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) Map<String, String> map) {
		log.info("listArticle map - {}", map);
		try {
			BoardListDto boardListDto = noticeService.listArticle(map);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("appli                                                     cation", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(boardListDto);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/{articleno}")
	public ResponseEntity<BoardDto> getArticle(
			@PathVariable("articleno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int articleno)
			throws Exception {
		log.info("getArticle - 호출 : " + articleno);
		noticeService.updateHit(articleno);
		return new ResponseEntity<BoardDto>(noticeService.getArticle(articleno), HttpStatus.OK);
	}

	@ApiOperation(value = "수정 할 글 얻기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/modify/{articleno}")
	public ResponseEntity<BoardDto> getModifyArticle(
			@PathVariable("articleno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int articleno)
			throws Exception {
		log.info("getModifyArticle - 호출 : " + articleno);
		return new ResponseEntity<BoardDto>(noticeService.getArticle(articleno), HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 글수정", notes = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyArticle(
			@RequestBody @ApiParam(value = "수정할 글정보.", required = true) BoardDto boardDto) throws Exception {
		log.info("modifyArticle - 호출 {}", boardDto);
		noticeService.modifyArticle(boardDto);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{articleno}")
	public ResponseEntity<String> deleteArticle(@PathVariable("articleno") @ApiParam(value = "살제할 글의 글번호.", required = true) int articleno) throws Exception {
		log.info("deleteArticle - 호출");
		noticeService.deleteArticle(articleno);
		return ResponseEntity.ok().build();
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 댓글 관련 추가 사항
	@ApiOperation(value = "게시판 댓글보기", notes = "글번호에 해당하는 게시글의 댓글 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/comments/{articleno}")
	public ResponseEntity<List<CommentDto>> getCommentsByArticleNo(
			@PathVariable("articleno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int articleno)
			throws Exception {
		log.info("getCommentsByArticleNo - 호출 : " + articleno);
		List<CommentDto> list = noticeService.getComments(articleno);

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 댓글 작성", notes = "새로운 댓글 내용을 입력한다.")
	@PostMapping("/comments")
	public ResponseEntity<?> writeComment(
			@RequestBody @ApiParam(value = "게시글 정보.", required = true) CommentDto commentDto) {
		log.info("writeComment commentDto - {}", commentDto);
		try {
			noticeService.writeComment(commentDto);
//			return ResponseEntity.ok().build();
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "게시판 댓글 삭제", notes = "해당하는 commentId의 댓글을 삭제한다.")
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<?> deleteComment(
			@PathVariable("commentId") @ApiParam(value = "얻어올 글의 글번호.", required = true) int commentId) {
		log.info("deleteComment - 호출");
		try {
			noticeService.deleteComment(commentId);
//			return ResponseEntity.ok().build();
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

}