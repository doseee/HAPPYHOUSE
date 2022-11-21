package com.ssafy.happyhouse.apt.model;

import lombok.Data;

@Data
public class HouseInfoDto {
	private String aptCode;
	private String roadName;
	private String roadNameBonbun;
	private String roadNameBubun;
	private String roadNameSeq;
	private String roadNameCode;
	private int buildYear;
	private String bonbun;
	private String apartmentName;
	private String dongCode;
	private String dong;
	private String jibun;
	private String lat;
	private String lng;
	private String sidoname;
	private String gugunname;
	private String avgPrice;
	private double avgRate;
	private int cnt;
}
