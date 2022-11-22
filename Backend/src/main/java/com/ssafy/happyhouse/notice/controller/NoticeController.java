package com.ssafy.happyhouse.notice.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.board.model.BoardDto;
import com.ssafy.happyhouse.notice.model.NoticeDto;
import com.ssafy.happyhouse.notice.model.service.NoticeService;

@RestController
@RequestMapping("/notice")
@CrossOrigin("*")
public class NoticeController {

	private final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	private final NoticeService noticeService;

	@Autowired
	public NoticeController(NoticeService noticeService) {
		logger.info("Notice Controller 생성자 호출");
		this.noticeService = noticeService;
	}
	
	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody NoticeDto noticeDto) throws Exception {
		try {
			noticeService.writeNotice(noticeDto);
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("data", "success");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		}
		catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/list")
	public ResponseEntity<?> list() throws Exception{
		try {
			List<NoticeDto> list = noticeService.listNotice();
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/view")
	public ResponseEntity<?> view(@RequestParam("articleNo") int articleNo)
			throws Exception {
		try {
			NoticeDto noticeDto = noticeService.getNotice(articleNo);
			noticeService.updateHit(articleNo);
			if( noticeDto != null)
				return new ResponseEntity<NoticeDto>(noticeDto, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody NoticeDto noticeDto, @RequestParam Map<String, String> map) throws Exception {
		try {
			noticeService.modifyNotice(noticeDto);
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("data", "success");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam("articleNo") int articleNo, @RequestParam Map<String, String> map) throws Exception {
		try {
			noticeService.deleteNotice(articleNo);
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("data", "success");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/searchTitle")
	public  ResponseEntity<?> searchByTitle(@RequestParam("subject") String subject, @RequestParam Map<String, String> map) throws Exception{
	    try {
	    	noticeService.searchByTitle(subject);
	    	Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("data", "success");
			return new ResponseEntity<List<NoticeDto>>(noticeService.searchByTitle(subject), HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(SQLException e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}