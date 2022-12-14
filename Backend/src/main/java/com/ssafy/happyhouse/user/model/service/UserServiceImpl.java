package com.ssafy.happyhouse.user.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.apt.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.board.model.BoardDto;
import com.ssafy.happyhouse.board.model.CommentDto;
import com.ssafy.happyhouse.board.model.mapper.BoardMapper;
import com.ssafy.happyhouse.board.model.mapper.CommentMapper;
import com.ssafy.happyhouse.user.model.UserDto;
import com.ssafy.happyhouse.user.model.mapper.UserMapper;


@Service
public class UserServiceImpl implements UserService {
	
	private UserMapper userMapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
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
	
	//???????????? ??????
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

	//???????????? ??????
	@Override
	public List<SidoGugunCodeDto> listLikeDong(String userid) throws Exception {
		return userMapper.listLikeDong(userid);

	}

	@Override
	public int pwdCheck(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.pwdCheck(map);
	}
	
	@Override
	public int nameCheck(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.nameCheck(map);
	}

	@Override
	public void modifyPwd(Map<String, String>map) throws Exception {
		// TODO Auto-generated method stub
		userMapper.modifyPwd(map);
	}

	@Override
	public List<BoardDto> getMyArticle(String userId) throws Exception {
		// TODO Auto-generated method stub
		return boardMapper.getMyArticle(userId);
	}

	@Override
	public List<CommentDto> getMyComment(String userId) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.getMyComment(userId);
	}				
}
