package com.ssafy.happyhouse.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.user.model.UserDto;
import com.ssafy.happyhouse.user.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.user.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@Api("User RestController V1")
public class UserController {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private UserService userService;

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping(value = "/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디(userId), 비밀번호(userPwd).", required = true) Map<String,String> map) {
		HttpStatus status = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserDto loginUser = userService.loginUser(map);
			if (loginUser != null) {
				String token = jwtService.create("userId", loginUser.getUserId() , "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("userId", loginUser.getUserId());
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원조회", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/user/{userId}")
	public ResponseEntity<Map<String, Object>> getUser(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.(userId)", required = true) String userId,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.getUser(userId);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원수정", notes = "수정된 회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@PutMapping("/user")
	public ResponseEntity<?> updateUser(
			@RequestBody @ApiParam(value = "수정할 회원의 아이디.(userId)", required = true) UserDto userDto) {
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.updateUser(userDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Void>(status);
	}
	
	@ApiOperation(value = "회원등록", notes = "수정된 회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@PostMapping("/user")
	public ResponseEntity<?> joinUser(@RequestBody UserDto userDto) {
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.joinUser(userDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Void>(status);
	}
	
	@ApiOperation(value = "비밀번호 찾기")
	@GetMapping("/user/findPwd/{userId}")
	public ResponseEntity<?> findPwd(@PathVariable("userId") String userId) {
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			String result = userService.findPwd(userId);
			if(result != null) {
				int leftLimit = 97; // letter 'a'
			    int rightLimit = 122; // letter 'z'
			    int targetStringLength = 10;
			    Random random = new Random();
			    String generatedString = random.ints(leftLimit, rightLimit + 1)
			                                   .limit(targetStringLength)
			                                   .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
			                                   .toString();
			    Map<String, String> map = new HashMap<>();
			    map.put("userId", userId);
			    map.put("newPass", generatedString);
			    userService.makePwd(map);
			    return new ResponseEntity<String>(result, status);
			}
			else {
				status = HttpStatus.NO_CONTENT;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Void>(status);
	}
	
	@ApiOperation(value = "회원 삭제")
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId) {
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.deleteUser(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Void>(status);
	}
}
