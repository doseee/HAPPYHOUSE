package com.ssafy.happyhouse.board.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			Map<String, Object> resultMap = new HashMap<>();
			boardService.writeArticle(boardDto);
			resultMap.put("data", "success");
			redirectAttributes.addAttribute("pgno", "1");
			redirectAttributes.addAttribute("key", "");
			redirectAttributes.addAttribute("word", "");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
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
	public ResponseEntity<?> view(@RequestParam("articleNo") int articleNo, @RequestParam Map<String, String> map, Model model)
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
	
	//내가 쓴 글
	@GetMapping("/myArticle")
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
	public ResponseEntity<?> modify(@RequestBody BoardDto boardDto, @RequestParam Map<String, String> map,
			RedirectAttributes redirectAttributes) throws Exception {
		try {
			boardService.modifyArticle(boardDto);
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("data", "success");
			redirectAttributes.addAttribute("pgno", map.get("pgno"));
			redirectAttributes.addAttribute("key", map.get("key"));
			redirectAttributes.addAttribute("word", map.get("word"));
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam("articleNo") int articleNo, @RequestParam Map<String, String> map,
			RedirectAttributes redirectAttributes) throws Exception {
		try {
			boardService.deleteArticle(articleNo);
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("data", "success");
			redirectAttributes.addAttribute("pgno", map.get("pgno"));
			redirectAttributes.addAttribute("key", map.get("key"));
			redirectAttributes.addAttribute("word", map.get("word"));
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	
	private ResponseEntity<String> exceptionHandling(SQLException e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}