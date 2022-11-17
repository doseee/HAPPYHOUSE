package com.ssafy.happyhouse.user.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.user.model.UserDto;

@Mapper
public interface UserMapper {
	int idCheck(String userId) throws SQLException;
	void joinUser(UserDto userDto) throws SQLException;
	UserDto loginUser(Map<String, String> map) throws SQLException;
	UserDto getUser(String userId) throws SQLException;
	void deleteUser(String userId) throws SQLException;
	void updateUser(UserDto userDto) throws SQLException;
	String findPwd(String userId) throws SQLException;
	void makePwd(Map<String, String> map) throws SQLException;
}
