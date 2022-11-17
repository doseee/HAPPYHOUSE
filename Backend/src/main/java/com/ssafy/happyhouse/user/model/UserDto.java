package com.ssafy.happyhouse.user.model;

import lombok.Data;

@Data
public class UserDto {
	private String userId;
	private String userPwd;
	private String userName;
	private String userTel;
	private String userEmail;
	private String userAddress;
	private String joinDate;
}
