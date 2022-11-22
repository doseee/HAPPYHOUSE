package com.ssafy.happyhouse.board.controller;

import java.sql.SQLException;
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
import com.ssafy.happyhouse.board.model.CommentDto;
import com.ssafy.happyhouse.board.model.service.CommentService;

@RestController
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {

	private final Logger logger = LoggerFactory.getLogger(CommentController.class);

	private final CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		logger.info("Comment Controller 생성자 호출");
		this.commentService = commentService;
	}
	
	//세션 추가
	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody CommentDto commentDto, RedirectAttributes redirectAttributes) throws Exception {
		try {
			commentService.writeComment(commentDto);
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
	public ResponseEntity<?> list(@RequestParam("articleNo") int articleNo, @RequestParam Map<String, String> map)
			throws Exception {
		try {
			List<CommentDto> list = commentService.listComment(map, articleNo);
			if( list != null)
				return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	
	//내가 쓴 글
	@GetMapping("/myComment")
	public ResponseEntity<?> myArticle(@RequestParam("userId") String userId) throws Exception{
		try {;
			List<CommentDto> list = commentService.getListCommentByUser(userId);
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody CommentDto commentDto, @RequestParam Map<String, String> map,
			RedirectAttributes redirectAttributes) throws Exception {
		try {
			commentService.modifyComment(commentDto);
			redirectAttributes.addAttribute("pgno", map.get("pgno"));
			redirectAttributes.addAttribute("key", map.get("key"));
			redirectAttributes.addAttribute("word", map.get("word"));
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam("commentNo") int commentNo, @RequestParam Map<String, String> map,
			RedirectAttributes redirectAttributes) throws Exception {
		try {
			commentService.deleteComment(commentNo);
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