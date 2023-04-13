package com.mysite.springmemberboard02.comment;

import java.util.List;

public interface CommentService {
	public int commentCount(int bno) throws Exception;
	public List<CommentVO> commentListService(int bno) throws Exception;
    public int commentInsertService(CommentVO comment) throws Exception;
    public int commentUpdateService(CommentVO comment) throws Exception;
    public int commentDeleteService(int cno) throws Exception;

}
