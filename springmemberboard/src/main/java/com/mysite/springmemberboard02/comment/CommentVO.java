package com.mysite.springmemberboard02.comment;

import java.sql.Date;

import lombok.Data;

@Data
public class CommentVO {
	private int cno; // 댓글 글번호
    private int bno; // 게시판 글번호
    private String content; // 댓글 내용
    private String writer;  // 댓글쓴이
    private Date reg_date;  // 댓글쓴 날짜
}
