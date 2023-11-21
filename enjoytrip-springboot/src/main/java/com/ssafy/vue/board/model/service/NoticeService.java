package com.ssafy.vue.board.model.service;

import com.ssafy.vue.board.model.BoardDto;
import com.ssafy.vue.board.model.BoardListDto;
import com.ssafy.vue.board.model.CommentDto;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    void writeArticle(BoardDto boardDto) throws Exception;
    BoardListDto listArticle(Map<String, String> map) throws Exception;
    //	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
    BoardDto getArticle(int articleNo) throws Exception;
    void updateHit(int articleNo) throws Exception;

    void modifyArticle(BoardDto boardDto) throws Exception;
    //
    void deleteArticle(int articleNo) throws Exception;

    List<CommentDto> getComments(int articleNo) throws Exception;

    void writeComment(CommentDto commentDto) throws Exception;

    void deleteComment(int commentId) throws Exception;
}