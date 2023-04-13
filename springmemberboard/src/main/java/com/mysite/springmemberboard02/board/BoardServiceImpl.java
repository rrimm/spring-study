package com.mysite.springmemberboard02.board;

import java.util.List;

import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
private BoardMapper mapper;
	
	public BoardServiceImpl(BoardMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public int getListCount() {
		int res = mapper.getListCount();
		return res;
	}

	@Override
	public List<BoardVO> getBoardList(int page, int limit) {
		int start = (page - 1) * limit;
		List<BoardVO> boardlist = mapper.getBoardList(start, limit);
		return boardlist;
	}
	
	@Override
	public boolean boardInsert(BoardVO board) { // 원글쓰기
		board.setRe_ref(mapper.getMaxNum() + 1);
		boolean res = mapper.boardInsert(board);
		return res;
	}
	
	@Override
	public BoardVO getDetail(int num) {
		mapper.setReadCountUpdate(num);
		BoardVO board = mapper.getDetail(num);
		return board;
	}
	
	@Override
	public BoardVO boardReplyForm(int num) {
		BoardVO board = mapper.getDetail(num);
		return board;
	}
	
	@Override
	public int boardReply(BoardVO vo) {
		mapper.boardReplyUpdate_reseq(vo); // 기존 답글의 순서를 조정하는 작업(새로 추가할 답글보다 뒤애 나올 답글의 re_seq값을 1증가시켜 순서를 유지하도록 한다)
		vo.setRe_seq(vo.getRe_seq()+1); // 새로운 답글의 순서 조정(원글의 순서보다 하나 크게 함)
		vo.setRe_lev(vo.getRe_lev()+1); // 새로운 답글의 들여쓰기 조정(원글의 순서보다 한단계 들여쓰기 함)
		int res = mapper.boardReply(vo);
		return res;
	}
	
	@Override
	public BoardVO boardModifyForm(int num) {
		BoardVO board = mapper.getDetail(num);
		return board;
	}
	
	@Override
	public boolean boardModify(BoardVO vo) {
		boolean res =  mapper.isBoardWriter(vo.getNum(), vo.getId());  // 수정할 글의 글쓴이와 일치하는지 검사
		if (res == true) {
			res =  mapper.boardModify(vo);
		}
		return res;
	}
	
	@Override
	public boolean boardDelete(int num, String id) {
		boolean res =  mapper.isBoardWriter(num, id);
		if (res == true) {
			res =  mapper.boardDelete(num);
		}
		return res;
	}
	

}
