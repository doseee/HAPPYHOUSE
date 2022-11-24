package com.ssafy.happyhouse.notice.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.notice.model.NoticeDto;

@Mapper
public interface NoticeMapper {
	int writeNotice(NoticeDto noticeDto) throws SQLException;
	List<NoticeDto> listNotice() throws SQLException;
	int getTotalNoticeCount(Map<String, Object> map) throws SQLException;
	NoticeDto getNotice(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	void modifyNotice(NoticeDto noticeDto) throws SQLException;
	void deleteNotice(int articleNo) throws SQLException;
	List<NoticeDto> searchByTitle(String searchTitle) throws SQLException;
	List<String> getNavNotice() throws SQLException;
}
