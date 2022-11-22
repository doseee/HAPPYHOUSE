package com.ssafy.happyhouse.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.board.model.BoardDto;
import com.ssafy.happyhouse.board.model.mapper.BoardMapper;


@Service
public class BoardServiceImpl implements BoardService {

	private BoardMapper boardMapper;

	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Override
	@Transactional
	public void writeArticle(BoardDto boardDto) throws Exception {
		System.out.println("글입력 전 dto : " + boardDto);
		boardMapper.writeArticle(boardDto);
		System.out.println("글입력 후 dto : " + boardDto);
	}

	@Override
	public List<BoardDto> listArticle() throws Exception {
		return boardMapper.listArticle();
	}

	@Override
	public BoardDto getArticle(int articleNo) throws Exception {
		return boardMapper.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		boardMapper.updateHit(articleNo);
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws Exception {
		boardMapper.modifyArticle(boardDto);
	}

	@Override
	@Transactional
	public void deleteArticle(int articleNo) throws Exception {
		boardMapper.deleteArticle(articleNo);
	}

	@Override
	public List<BoardDto> getListArticleByUser(String userId) throws Exception {
		return boardMapper.getListArticleByUser(userId);
	}

	@Override
	public List<BoardDto> searchByTitle(String searchTitle) throws Exception {
		return boardMapper.searchByTitle(searchTitle);
	}

}
