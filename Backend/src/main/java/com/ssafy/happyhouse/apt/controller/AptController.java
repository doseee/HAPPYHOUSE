package com.ssafy.happyhouse.apt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.apt.model.AptDto;
import com.ssafy.happyhouse.apt.model.service.AptService;


@RestController
@RequestMapping("/apt")
@CrossOrigin("*")
public class AptController {
	public static final Logger logger = LoggerFactory.getLogger(AptController.class);
	
	private final AptService aptService;

	@Autowired
	public AptController(AptService aptService) {
		logger.info("Apt Controller 생성자 호출");
		this.aptService = aptService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getList(@RequestBody AptDto aptDto) {
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			List<AptDto> resultList = aptService.getList(aptDto);
			if(resultList != null && !resultList.isEmpty())
				return new ResponseEntity<List<AptDto>>(resultList, status);
			else
				status = HttpStatus.NO_CONTENT;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Void>(status);
	}
}
