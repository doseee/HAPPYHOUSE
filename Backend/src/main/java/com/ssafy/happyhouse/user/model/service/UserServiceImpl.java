package com.ssafy.happyhouse.user.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.apt.model.SidoGugunCodeDto;
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
	public UserDto loginUser(UserDto userDto) throws Exception {
		return userMapper.loginUser(userDto);
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
	
	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", refreshToken);
		userMapper.saveRefreshToken(map);
	}
	
	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return userMapper.getRefreshToken(userId);
	}

	@Override
	public void deleRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		userMapper.deleteRefreshToken(map);
	}
	
	//관심지역 추가
	@Override
	public void addLikeDong(Map<String, String> map) throws Exception {
		userMapper.addLikeDong(map);
	}
	
	@Override
	public boolean deleteLikeDong(Map<String, String> map) throws Exception {
		int n= userMapper.deleteLikeDong(map);
		System.out.println(n);
		return n==1;
	}

	//관심지역 조회
	@Override
	public List<SidoGugunCodeDto> listLikeDong(String userid) throws Exception {
		return userMapper.listLikeDong(userid);

	}				
}
