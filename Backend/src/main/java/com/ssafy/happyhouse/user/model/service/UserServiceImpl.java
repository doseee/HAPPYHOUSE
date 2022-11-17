package com.ssafy.happyhouse.user.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.user.model.UserDto;
import com.ssafy.happyhouse.user.model.mapper.UserMapper;


@Service
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;
	
	@Autowired
	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	@Override
	public int idCheck(String userId) throws Exception {
		return userMapper.idCheck(userId);
	}
	
	@Override
	public void joinUser(UserDto userDto) throws Exception {
		userMapper.joinUser(userDto);
	}

	@Override
	public UserDto loginUser(Map<String, String> map) throws Exception {
		return userMapper.loginUser(map);
	}
	@Override
	public UserDto getUser(String userId) throws Exception {
		return userMapper.getUser(userId);
	}

	@Override
	public void deleteUser(String userId) throws Exception {
		userMapper.deleteUser(userId);
		
	}

	@Override
	public void updateUser(UserDto userDto) throws Exception {
		userMapper.updateUser(userDto);
	}

	@Override
	public String findPwd(String userId) throws Exception {
		return userMapper.findPwd(userId);
	}

	@Override
	public void makePwd(Map<String, String> map) throws Exception {
		userMapper.makePwd(map);
	}
}
