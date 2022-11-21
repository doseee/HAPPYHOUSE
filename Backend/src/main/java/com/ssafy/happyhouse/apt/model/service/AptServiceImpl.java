package com.ssafy.happyhouse.apt.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.apt.model.HouseDealDto;
import com.ssafy.happyhouse.apt.model.HouseInfoDto;
import com.ssafy.happyhouse.apt.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.apt.model.mapper.AptMapper;

@Service
public class AptServiceImpl implements AptService {
	
	
	private AptMapper aptMapper;
	
	@Autowired
	public AptServiceImpl(AptMapper aptMapper) {
		this.aptMapper = aptMapper;
	}

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return aptMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return aptMapper.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return aptMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return aptMapper.getAptInDong(dong);
	}
	
	@Override
	public List<HouseInfoDto> getAptName(String aptName) throws Exception {
		return aptMapper.getAptName(aptName);
	}
	
	@Override
	public List<HouseDealDto> getDealsByCode(String aptCode) throws Exception {
		return aptMapper.getDealsByCode(aptCode);
	}

	@Override
	public SidoGugunCodeDto getAddressName(String dongCode) throws Exception {
		return aptMapper.getAddressName(dongCode);
	}
	
	@Override
	public HouseInfoDto selectHouse(String aptCode) throws Exception {
		return aptMapper.selectHouse(aptCode);
	}
}
