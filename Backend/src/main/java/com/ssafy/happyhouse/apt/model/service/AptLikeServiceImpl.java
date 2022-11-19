package com.ssafy.happyhouse.apt.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.apt.model.AptLikeDto;
import com.ssafy.happyhouse.apt.model.mapper.AptLikeMapper;
import com.ssafy.happyhouse.util.PageNavigation;
import com.ssafy.happyhouse.util.SizeConstant;

@Service
public class AptLikeServiceImpl implements AptLikeService{
	
	private AptLikeMapper aptLikeMapper;
	
	@Autowired
	public AptLikeServiceImpl(AptLikeMapper aptLikeMapper) {
		this.aptLikeMapper = aptLikeMapper;
	}

	@Override
	public void addList(AptLikeDto aptLikeDto) throws Exception {
		aptLikeMapper.addList(aptLikeDto);
	}

	@Override
	public void deleteList(int likeNo) throws Exception {
		aptLikeMapper.deleteList(likeNo);
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
		int totalCount = aptLikeMapper.getTotalCount(param);
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
	public List<AptLikeDto> getList(String userId) throws Exception {
		return aptLikeMapper.getList(userId);
	}
}
