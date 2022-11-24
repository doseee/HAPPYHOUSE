package com.ssafy.happyhouse.board.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.board.model.BoardDto;

public interface BoardService {
	void writeArticle(BoardDto boardDto) throws Exception;
	List<BoardDto> listArticle() throws Exception;
	BoardDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	void modifyArticle(BoardDto boardDto) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	List<BoardDto> getListArticleByUser(String userId) throws Exception;
	List<BoardDto> searchByTitle(String searchTitle) throws Exception;
	List<BoardDto> getBestArticle() throws Exception;
}
