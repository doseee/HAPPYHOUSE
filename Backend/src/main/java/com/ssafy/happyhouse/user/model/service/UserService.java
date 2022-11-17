package com.ssafy.happyhouse.user.model.service;

import java.util.Map;

import com.ssafy.happyhouse.user.model.UserDto;

public interface UserService {
	
	int idCheck(String userId) throws Exception;
	void joinUser(UserDto userDto) throws Exception;
	UserDto loginUser(Map<String, String> map) throws Exception;
	
	void deleteUser(String userId) throws Exception;
	void updateUser(UserDto userDto) throws Exception;
	
	UserDto getUser(String userId) throws Exception;
	String findPwd(String userId) throws Exception;
	void makePwd(Map<String, String> map) throws Exception;
}
