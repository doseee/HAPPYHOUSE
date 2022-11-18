package com.ssafy.happyhouse.notice.model;

import lombok.Data;

@Data
public class NoticeDto {
	private int articleNo;
	private String subject;
	private String content;
	private int hit;
	private String registerTime;
}
