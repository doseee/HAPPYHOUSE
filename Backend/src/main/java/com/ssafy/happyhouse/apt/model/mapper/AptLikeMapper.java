package com.ssafy.happyhouse.apt.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.apt.model.AptLikeDto;

@Mapper
public interface AptLikeMapper {
	void addList(AptLikeDto aptLikeDto) throws SQLException;
	void deleteList(int likeNo) throws SQLException;
	int getTotalCount(Map<String, Object> map) throws SQLException;
	List<AptLikeDto> getList(String userId) throws SQLException;
}
