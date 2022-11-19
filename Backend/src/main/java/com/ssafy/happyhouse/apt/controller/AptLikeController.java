package com.ssafy.happyhouse.apt.controller;

import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.happyhouse.apt.model.AptLikeDto;
import com.ssafy.happyhouse.apt.model.service.AptLikeService;

@RestController
@RequestMapping("/aptLike")
@CrossOrigin("*")
public class AptLikeController {
	
	private static final Logger logger = LoggerFactory.getLogger(AptLikeController.class);
	
	private final AptLikeService aptLikeService;

	@Autowired
	public AptLikeController(AptLikeService aptLikeService) {
		logger.info("AptLike Controller 생성자 호출");
		this.aptLikeService = aptLikeService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody AptLikeDto aptLikeDto, RedirectAttributes redirectAttributes) throws Exception {
		try {
			aptLikeService.addList(aptLikeDto);
			redirectAttributes.addAttribute("pgno", "1");
			redirectAttributes.addAttribute("key", "");
			redirectAttributes.addAttribute("word", "");
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam("likeNo") int likeNo, @RequestParam Map<String, String> map,
			RedirectAttributes redirectAttributes) throws Exception {
		try {
			aptLikeService.deleteList(likeNo);
			redirectAttributes.addAttribute("pgno", map.get("pgno"));
			redirectAttributes.addAttribute("key", map.get("key"));
			redirectAttributes.addAttribute("word", map.get("word"));
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> list(@RequestParam("userId") String userId) throws Exception{
		try {
			List<AptLikeDto> list = aptLikeService.getList(userId);
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<AptLikeDto>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(SQLException e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
