package com.mysite.springmemberboard02.board;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardVO {
	private int num;
	private String id;
	private String subject;
	private String content;
	private MultipartFile uploadfile; // 파일 업로드를 위한 필드
	private String org_file; // 원본 파일 이름 저장
	private String up_file;  // 업로드된 파일 이름 저장
	private int re_ref;
	private int re_lev;
	private int re_seq;
	private int readcount;
	private Date boarddate;

}
