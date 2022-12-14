package com.ssafy.happyhouse.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.board.model.BoardDto;

@Mapper
public interface BoardMapper {
	int writeArticle(BoardDto boardDto) throws SQLException;
	List<BoardDto> listArticle() throws SQLException;
	int getTotalArticleCount(Map<String, Object> map) throws SQLException;
	BoardDto getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	void modifyArticle(BoardDto boardDto) throws SQLException;
	void deleteArticle(int articleNo) throws SQLException;
	List<BoardDto> getListArticleByUser(String userId) throws SQLException;
	List<BoardDto> searchByTitle(String searchTitle) throws SQLException;
	List<BoardDto> getMyArticle(String userId) throws SQLException;
	List<BoardDto> getBestArticle() throws SQLException;
}
