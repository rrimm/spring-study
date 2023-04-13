package com.mysite.springmemberboard02.board;

import java.util.List;

public interface BoardService {
	public int getListCount();
	public List<BoardVO> getBoardList(int page,int limit);
	public boolean boardInsert(BoardVO board);
	public BoardVO getDetail(int num);
	public int boardReply(BoardVO board);
	public BoardVO boardReplyForm(int num);
	public BoardVO boardModifyForm(int num);
	public boolean boardModify(BoardVO modifyboard);
	public boolean boardDelete(int num, String id);

}
