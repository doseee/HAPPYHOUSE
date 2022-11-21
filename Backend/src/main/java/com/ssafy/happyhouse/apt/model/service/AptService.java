package com.ssafy.happyhouse.apt.model.service;

import java.util.List;

import com.ssafy.happyhouse.apt.model.HouseDealDto;
import com.ssafy.happyhouse.apt.model.HouseInfoDto;
import com.ssafy.happyhouse.apt.model.SidoGugunCodeDto;

public interface AptService {
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getAptName(String aptName) throws Exception;
	List<HouseDealDto> getDealsByCode(String aptCode) throws Exception;
	SidoGugunCodeDto getAddressName(String dongCode) throws Exception;
	HouseInfoDto selectHouse(String aptCode)throws Exception;
}
