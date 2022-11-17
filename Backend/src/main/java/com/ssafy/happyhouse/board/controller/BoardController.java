package com.ssafy.happyhouse.board.controller;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.happyhouse.board.model.BoardDto;
import com.ssafy.happyhouse.board.model.service.BoardService;
import com.ssafy.happyhouse.user.model.UserDto;
import com.ssafy.happyhouse.util.PageNavigation;

@RestController
@RequestMapping("/board")
@CrossOrigin("*")
public class BoardController {

	private final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private ServletContext servletContext;

	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		logger.info("Board Controller 생성자 호출");
		this.boardService = boardService;
	}

	@GetMapping("/write")
	public ResponseEntity<?> write(@RequestParam Map<String, String> map, Model model) {
		model.addAttribute("pgno", map.get("pgno"));
		model.addAttribute("key", map.get("key"));
		model.addAttribute("word", map.get("word"));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	//세션 추가
	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody BoardDto boardDto, RedirectAttributes redirectAttributes) throws Exception {
		try {
			boardService.writeArticle(boardDto);
			redirectAttributes.addAttribute("pgno", "1");
			redirectAttributes.addAttribute("key", "");
			redirectAttributes.addAttribute("word", "");
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/list")
	public ResponseEntity<?> list(@RequestParam Map<String, String> map) throws Exception{
		try {
			List<BoardDto> list = boardService.listArticle(map);
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/view")
	public ResponseEntity<?> view(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, Model model)
			throws Exception {
		try {
			BoardDto boardDto = boardService.getArticle(articleNo);
			boardService.updateHit(articleNo);
			model.addAttribute("article", boardDto);
			model.addAttribute("pgno", map.get("pgno"));
			model.addAttribute("key", map.get("key"));
			model.addAttribute("word", map.get("word"));
			if( boardDto != null)
				return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/modify")
	public ResponseEntity<?> modify(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, Model model)
			throws Exception {
		try {
			BoardDto boardDto = boardService.getArticle(articleNo);
			model.addAttribute("article", boardDto);
			model.addAttribute("pgno", map.get("pgno"));
			model.addAttribute("key", map.get("key"));
			model.addAttribute("word", map.get("word"));
			if( boardDto != null)
				return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody BoardDto boardDto, @RequestParam Map<String, String> map,
			RedirectAttributes redirectAttributes) throws Exception {
		try {
			boardService.modifyArticle(boardDto);
			redirectAttributes.addAttribute("pgno", map.get("pgno"));
			redirectAttributes.addAttribute("key", map.get("key"));
			redirectAttributes.addAttribute("word", map.get("word"));
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map,
			RedirectAttributes redirectAttributes) throws Exception {
		try {
			boardService.deleteArticle(articleNo, servletContext.getRealPath("/upload"));
			redirectAttributes.addAttribute("pgno", map.get("pgno"));
			redirectAttributes.addAttribute("key", map.get("key"));
			redirectAttributes.addAttribute("word", map.get("word"));
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	
	private ResponseEntity<String> exceptionHandling(SQLException e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}