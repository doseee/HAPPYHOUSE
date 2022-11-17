package com.ssafy.happyhouse.apt.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.apt.model.AptDto;
import com.ssafy.happyhouse.apt.model.mapper.AptMapper;

@Service
public class AptServiceImpl implements AptService {
	private AptMapper aptMapper;

	@Autowired
	public AptServiceImpl(AptMapper aptMapper) {
		this.aptMapper = aptMapper;
	}

	@Override
	public List<AptDto> getList(AptDto aptDto) throws Exception {
		// TODO Auto-generated method stub
		return aptMapper.getList(aptDto);
	}

}
