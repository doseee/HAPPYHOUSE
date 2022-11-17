package com.ssafy.happyhouse.apt.model.service;

import java.util.List;

import com.ssafy.happyhouse.apt.model.AptDto;

public interface AptService {
	List<AptDto> getList(AptDto aptDto) throws Exception;
}
