package com.ssafy.vue.board.model.service;

import com.ssafy.vue.board.model.BoardDto;
import com.ssafy.vue.board.model.BoardListDto;
import com.ssafy.vue.board.model.CommentDto;
import com.ssafy.vue.board.model.FileInfoDto;
import com.ssafy.vue.board.model.mapper.BoardMapper;
import com.ssafy.vue.board.model.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {

	private NoticeMapper noticeMapper;

	@Autowired
	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		super();
		this.noticeMapper = noticeMapper;
	}

	@Override
	@Transactional
	public void writeArticle(BoardDto boardDto) throws Exception {
		noticeMapper.writeArticle(boardDto);
		List<FileInfoDto> fileInfos = boardDto.getFileInfos();
		if (fileInfos != null && !fileInfos.isEmpty()) {
			noticeMapper.registerFile(boardDto);
		}
	}

	@Override
	public BoardListDto listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
		int start = currentPage * sizePerPage - sizePerPage;
		param.put("start", start);
		param.put("listsize", sizePerPage);

		String key = map.get("key");
		param.put("key", key == null ? "" : key);
		if ("user_id".equals(key))
			param.put("key", key == null ? "" : "b.user_id");
		List<BoardDto> list = noticeMapper.listArticle(param);

		if ("user_id".equals(key))
			param.put("key", key == null ? "" : "user_id");
		int totalArticleCount = noticeMapper.getTotalArticleCount(param);
		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

		BoardListDto boardListDto = new BoardListDto();
		boardListDto.setArticles(list);
		boardListDto.setCurrentPage(currentPage);
		boardListDto.setTotalPageCount(totalPageCount);

		return boardListDto;
	}

	@Override
	public BoardDto getArticle(int articleNo) throws Exception {
		return noticeMapper.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		noticeMapper.updateHit(articleNo);
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws Exception {
		// TODO : BoardDaoImpl의 modifyArticle 호출
		noticeMapper.modifyArticle(boardDto);
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		noticeMapper.deleteArticle(articleNo);
	}

	@Override
	public List<CommentDto> getComments(int articleNo) throws Exception {
//		List<CommentDto> list = boardMapper.listComment(articleNo);
		return noticeMapper.listComment(articleNo);
	}

	@Override
	public void writeComment(CommentDto commentDto) throws Exception {
		noticeMapper.writeComment(commentDto);
	}

	@Override
	public void deleteComment(int commentId) throws Exception {
		noticeMapper.deleteComment(commentId);
	}

}
