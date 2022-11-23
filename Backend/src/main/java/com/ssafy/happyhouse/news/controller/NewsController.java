package com.ssafy.happyhouse.news.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.news.model.service.NewsService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/news")
public class NewsController {
	
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);;
	
	private NewsService newsService;
	
	@Autowired
	public NewsController(NewsService newsService) {
		logger.debug("News Controller 생성자 호출");
		this.newsService = newsService;
	}

	@GetMapping("/list")
	public ResponseEntity<?> newsList() throws Exception{
		String list = newsService.newsList();
		if(list != null && !list.isEmpty()) 
			return new ResponseEntity<String>(list, HttpStatus.OK);
		else 
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}