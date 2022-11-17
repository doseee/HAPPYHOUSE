package com.ssafy.happyhouse.apt.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.apt.model.AptDto;

@Mapper
public interface AptMapper {
	List<AptDto> getList(AptDto aptDto) throws SQLException;
}
