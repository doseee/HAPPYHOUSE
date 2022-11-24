package com.ssafy.happyhouse.board.controller;

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
import com.ssafy.happyhouse.board.model.service.BoardService;

@RestController
@RequestMapping("/board")
@CrossOrigin("*")
public class BoardController {

	private final Logger logger = LoggerFactory.getLogger(BoardController.class);

	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		logger.info("Board Controller 생성자 호출");
		this.boardService = boardService;
	}
	
	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody BoardDto boardDto) throws Exception {
		try {
			Map<String, Object> resultMap = new HashMap<>();
			boardService.writeArticle(boardDto);
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
			List<BoardDto> list = boardService.listArticle();
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/best-article")
	public ResponseEntity<?> getBestArticle() throws Exception{
		Map<String, Object> map = new HashMap<>();
		try {
			List<BoardDto> list = boardService.getBestArticle();
			if(list != null && !list.isEmpty()) {
				map.put("data", list);
				map.put("result", "success");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping(value= {"/view", "/searchNo"})
	public ResponseEntity<?> view(@RequestParam("articleNo") int articleNo)
			throws Exception {
		try {
			BoardDto boardDto = boardService.getArticle(articleNo);
			boardService.updateHit(articleNo);
			if( boardDto != null)
				return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping(value= {"/myArticle", "/searchId"})
	public ResponseEntity<?> myArticle(@RequestParam("userId") String userId) throws Exception{
		try {;
			List<BoardDto> list = boardService.getListArticleByUser(userId);
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody BoardDto boardDto, @RequestParam Map<String, String> map) throws Exception {
		try {
			boardService.modifyArticle(boardDto);
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
			boardService.deleteArticle(articleNo);
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
	    	boardService.searchByTitle(subject);
	    	Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("data", "success");
			return new ResponseEntity<List<BoardDto>>(boardService.searchByTitle(subject), HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(SQLException e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}