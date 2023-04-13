package com.mysite.springmemberboard02.comment;

import java.util.List;

import org.springframework.stereotype.Service;

@Service("mCommentService")
public class CommentServiceImpl implements CommentService {
	
private CommentMapper mapper;
	
	public CommentServiceImpl(CommentMapper mapper) {
		this.mapper= mapper;		
	}

	@Override
	public int commentCount(int bno) throws Exception {
		return mapper.commentCount(bno);
	}

	@Override
	public List<CommentVO> commentListService(int bno) throws Exception {
		return mapper.commentList(bno);
	}

	@Override
	public int commentInsertService(CommentVO comment) throws Exception {
		return mapper.commentInsert(comment);
	}

	@Override
	public int commentUpdateService(CommentVO comment) throws Exception {
		return mapper.commentUpdate(comment);
	}

	@Override
	public int commentDeleteService(int cno) throws Exception {
		return mapper.commentDelete(cno);
	}

}
