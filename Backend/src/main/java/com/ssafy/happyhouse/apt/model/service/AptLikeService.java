package com.ssafy.happyhouse.apt.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.apt.model.AptLikeDto;
import com.ssafy.happyhouse.util.PageNavigation;

public interface AptLikeService {
	void addList(AptLikeDto aptLikeDto) throws Exception;
	void deleteList(int likeNo) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	List<AptLikeDto> getList(String userId) throws Exception;
}
