package com.ssafy.happyhouse.notice.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.notice.model.NoticeDto;
import com.ssafy.happyhouse.notice.model.mapper.NoticeMapper;
import com.ssafy.happyhouse.util.PageNavigation;
import com.ssafy.happyhouse.util.SizeConstant;

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
	public List<NoticeDto> listNotice(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);

		return noticeMapper.listNotice(param);
	}
	
	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		if ("userid".equals(key))
			key = "user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int totalCount = noticeMapper.getTotalNoticeCount(param);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
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
