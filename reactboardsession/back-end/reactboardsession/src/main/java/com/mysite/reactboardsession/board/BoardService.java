package com.mysite.reactboardsession.board;

import java.util.List;

public interface BoardService {
	public List<BoardVO> getBoardList(int page_num, int limit);
	public int getCount();
	public int insertBoard(BoardVO board);
	public BoardVO getDetail(BoardVO board);
	public int updateBoard(BoardVO board);
	public int deleteBoard(BoardVO board);
}
