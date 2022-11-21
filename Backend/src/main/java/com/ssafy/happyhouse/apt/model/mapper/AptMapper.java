package com.ssafy.happyhouse.apt.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.apt.model.HouseDealDto;
import com.ssafy.happyhouse.apt.model.HouseInfoDto;
import com.ssafy.happyhouse.apt.model.SidoGugunCodeDto;

@Mapper
public interface AptMapper {
	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	List<HouseInfoDto> getAptName(String aptName) throws SQLException;
	List<HouseDealDto> getDealsByCode(String aptCode) throws SQLException;
	SidoGugunCodeDto getAddressName(String dongCode) throws Exception;
	HouseInfoDto selectHouse(String aptCode)throws SQLException;
}
