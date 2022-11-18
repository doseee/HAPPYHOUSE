package com.ssafy.happyhouse.board.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.board.model.CommentDto;
import com.ssafy.happyhouse.util.PageNavigation;

public interface CommentService {
	void writeComment(CommentDto commentDto) throws Exception;
	List<CommentDto> listComment(Map<String, String> map, int articleNo) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	int getTotalCommentCount(Map<String, Object> map) throws Exception;
	void modifyComment(CommentDto commentDto) throws Exception;
	void deleteComment(int commentNo) throws Exception;
	List<CommentDto> getListCommentByUser(String userId) throws Exception;
}
