package com.mysite.reactspring;

import java.util.List;

public interface BoardService {
	public List<BoardVO> getBoardList();
	public int insertBoard(BoardVO board);
	public BoardVO getDetail(BoardVO board);
	public int updateBoard(BoardVO board);
	public int deleteBoard(BoardVO board);
}
