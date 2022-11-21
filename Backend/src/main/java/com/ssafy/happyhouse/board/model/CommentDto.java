package com.ssafy.happyhouse.board.model;

import lombok.Data;

@Data
public class CommentDto {
	private int commentNo;
	private String userId;
	private int articleNo;
	private String content;
	private String registerTime;
}
