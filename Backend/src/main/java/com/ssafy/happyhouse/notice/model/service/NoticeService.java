package com.ssafy.happyhouse.notice.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.notice.model.NoticeDto;
import com.ssafy.happyhouse.util.PageNavigation;

public interface NoticeService {
	void writeNotice(NoticeDto noticeDto) throws Exception;
	List<NoticeDto> listNotice(Map<String, String> map) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	NoticeDto getNotice(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	void modifyNotice(NoticeDto noticeDto) throws Exception;
	void deleteNotice(int articleNo) throws Exception;
}
