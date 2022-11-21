package com.ssafy.happyhouse.apt.model;

import lombok.Data;

@Data
public class AptLikeDto {
	private String userId;
	private int likeNo;
	private String sidoName;
	private String gugunName;
	private String dongName;
	private String dongCode;
}