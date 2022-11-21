package com.ssafy.happyhouse.apt.model;

import lombok.Data;

@Data
public class HouseDealDto {
	private String aptCode;
	private String AptName;
	private String dealAmount;
	private String dealYear;
	private String dealMonth;
	private String dealDay;
	private String area;
	private String floor;
	private String type;
}
