package com.ssafy.happyhouse.notice.model.service;

import java.util.List;

import com.ssafy.happyhouse.notice.model.NoticeDto;

public interface NoticeService {
	void writeNotice(NoticeDto noticeDto) throws Exception;
	List<NoticeDto> listNotice() throws Exception;
	NoticeDto getNotice(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	void modifyNotice(NoticeDto noticeDto) throws Exception;
	void deleteNotice(int articleNo) throws Exception;
	List<NoticeDto> searchByTitle(String subject) throws Exception;
	List<String> getNavNotice() throws Exception;
}
