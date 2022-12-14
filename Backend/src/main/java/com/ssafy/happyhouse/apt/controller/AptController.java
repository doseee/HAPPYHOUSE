package com.ssafy.happyhouse.apt.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.apt.model.HouseDealDto;
import com.ssafy.happyhouse.apt.model.HouseInfoDto;
import com.ssafy.happyhouse.apt.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.apt.model.service.AptService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/apt")
@CrossOrigin("*")
public class AptController {
	
	private final Logger logger = LoggerFactory.getLogger(AptController.class);

	@Autowired
	private AptService aptService;
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.debug("sido : {}", aptService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(aptService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		logger.debug("sido : {}", sido);
		return new ResponseEntity<List<SidoGugunCodeDto>>(aptService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		logger.debug("gugun : {}",gugun);
		return new ResponseEntity<List<HouseInfoDto>>(aptService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		logger.debug("dong : {}", dong);
		return new ResponseEntity<List<HouseInfoDto>>(aptService.getAptInDong(dong), HttpStatus.OK);
	}
	
	@GetMapping("/apt-name")
	public ResponseEntity<List<HouseInfoDto>> aptName(@RequestParam("aptName") String aptName) throws Exception {
		logger.debug("aptName : {}", aptName);
		return new ResponseEntity<List<HouseInfoDto>>(aptService.getAptName(aptName), HttpStatus.OK);
	}
	
	@GetMapping("/apt-deals")
	public ResponseEntity<List<HouseDealDto>> aptDeals(@RequestParam("aptCode") String aptCode) throws Exception {
		logger.debug("aptCode : {}", aptCode);
		return new ResponseEntity<List<HouseDealDto>>(aptService.getDealsByCode(aptCode), HttpStatus.OK);
	}
	

	@GetMapping("/address")
	public ResponseEntity<?> getAddressName(@RequestParam("dongcode")String dongcode) throws Exception{
		try {
			SidoGugunCodeDto dto = aptService.getAddressName(dongcode);
			if(dto != null) {
				return new ResponseEntity<SidoGugunCodeDto>(dto, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "????????? ??????", notes = "????????? ????????? ????????? ????????? ?????? ????????? ????????????.", response = String.class)
	@GetMapping("/aptinfo")
	public ResponseEntity<HouseInfoDto> selectHouse(@RequestParam String aptCode) throws Exception {
		logger.debug("aptCode : {}", aptCode);
		logger.debug("result : {}", aptService.selectHouse(aptCode));
		return new ResponseEntity<HouseInfoDto>(aptService.selectHouse(aptCode), HttpStatus.OK);
	}
	 
	 private ResponseEntity<String> exceptionHandling(SQLException e) {
		 return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	 }
}