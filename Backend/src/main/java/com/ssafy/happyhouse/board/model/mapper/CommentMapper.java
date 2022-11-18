package com.ssafy.happyhouse.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.board.model.CommentDto;

@Mapper
public interface CommentMapper {
	int writeComment(CommentDto commentDto) throws SQLException;
	List<CommentDto> listComment(Map<String, Object> map, int articleNo) throws SQLException;
	int getTotalCommentCount(Map<String, Object> map) throws SQLException;
	void modifyComment(CommentDto commentDto) throws SQLException;
	void deleteComment(int commentNo) throws SQLException;
	List<CommentDto> getListCommentByUser(String userId) throws SQLException;
}
