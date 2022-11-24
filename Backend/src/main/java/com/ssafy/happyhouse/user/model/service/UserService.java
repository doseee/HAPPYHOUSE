package com.ssafy.happyhouse.user.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.apt.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.board.model.BoardDto;
import com.ssafy.happyhouse.board.model.CommentDto;
import com.ssafy.happyhouse.user.model.UserDto;

public interface UserService {
	
	int idCheck(String userId) throws Exception;
	int pwdCheck(Map<String, String> map) throws Exception;
	int nameCheck(Map<String, String> map) throws Exception;
	void joinUser(UserDto userDto) throws Exception;
	UserDto loginUser(UserDto userDto) throws Exception;
	
	void saveRefreshToken(String userId, String refreshToken) throws Exception;
	Object getRefreshToken(String userId) throws Exception;
	void deleRefreshToken(String userId) throws Exception;
	
	void deleteUser(String userId) throws Exception;
	void updateUser(UserDto userDto) throws Exception;
	void modifyPwd(Map<String, String>map) throws Exception;
	
	
	UserDto getUser(String userId) throws Exception;
	String findPwd(String userId) throws Exception;
	void makePwd(Map<String, String> map) throws Exception;
	
	// 관심지역 등록
	void addLikeDong(Map<String, String> map) throws Exception;

	// 관심지역 조회
	List<SidoGugunCodeDto> listLikeDong(String userid) throws Exception;
	boolean deleteLikeDong(Map<String, String> map) throws Exception;
	
	List<BoardDto> getMyArticle(String userId) throws Exception;
	List<CommentDto> getMyComment(String userId) throws Exception;

}
