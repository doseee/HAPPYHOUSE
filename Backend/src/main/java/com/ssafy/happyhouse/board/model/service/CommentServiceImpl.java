package com.ssafy.happyhouse.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.board.model.mapper.CommentMapper;
import com.ssafy.happyhouse.board.model.CommentDto;

@Service
public class CommentServiceImpl implements CommentService{
	
	private CommentMapper commentMapper;
	
	@Autowired
	public CommentServiceImpl(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}

	@Override
	@Transactional
	public void writeComment(CommentDto commentDto) throws Exception {
		System.out.println("댓글입력 전 dto : " + commentDto);
		commentMapper.writeComment(commentDto);
		System.out.println("댓글입력 후 dto : " + commentDto);
	}
	
	@Override
	public List<CommentDto> listComment(int articleNo) throws Exception {
		return commentMapper.listComment(articleNo);
	}
	
	@Override
	public int getTotalCommentCount(Map<String, Object> map) throws Exception {
		return commentMapper.getTotalCommentCount(map);
	}
	
	@Override
	public void modifyComment(CommentDto commentDto) throws Exception {
		commentMapper.modifyComment(commentDto);	
	}

	@Override
	public void deleteComment(int commentNo) throws Exception {
		commentMapper.deleteComment(commentNo);
	}

	@Override
	public List<CommentDto> getListCommentByUser(String userId) throws Exception {
		return commentMapper.getListCommentByUser(userId);
	}
}
