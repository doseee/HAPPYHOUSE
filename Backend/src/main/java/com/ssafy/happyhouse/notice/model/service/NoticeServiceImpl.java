package com.ssafy.happyhouse.notice.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.notice.model.NoticeDto;
import com.ssafy.happyhouse.notice.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	private NoticeMapper noticeMapper;
	
	@Autowired
	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

	@Override
	@Transactional
	public void writeNotice(NoticeDto noticeDto) throws Exception {
		System.out.println("공지사항 입력 전 dto : " + noticeDto);
		noticeMapper.writeNotice(noticeDto);
		System.out.println("공지사항 입력 후 dto : " + noticeDto);
	}

	@Override
	public List<NoticeDto> listNotice() throws Exception {
		return noticeMapper.listNotice();
	}
	
	@Override
	public NoticeDto getNotice(int articleNo) throws Exception {
		return noticeMapper.getNotice(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		noticeMapper.updateHit(articleNo);
	}

	@Override
	public void modifyNotice(NoticeDto noticeDto) throws Exception {
		noticeMapper.modifyNotice(noticeDto);
	}

	@Override
	public void deleteNotice(int articleNo) throws Exception {
		noticeMapper.deleteNotice(articleNo);
	}
}
