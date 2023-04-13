package com.mysite.reactspring;

import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

    private BoardMapper mapper;

    public BoardServiceImpl(BoardMapper mapper) {

        this.mapper = mapper;
    }

    @Override
    public List<BoardVO> getBoardList() {
        List<BoardVO> boardlist = mapper.getBoardList();
        return boardlist;
    }

    @Override
    public BoardVO getDetail(BoardVO BoardVO) {
        BoardVO boardVO = mapper.getDetail(BoardVO);
        return boardVO;
    }


    @Override
    public int insertBoard(BoardVO BoardVO) {
        int res = mapper.insertBoard(BoardVO);
        return res;
    }

    @Override
    public int updateBoard(BoardVO BoardVO) {
        int res = mapper.updateBoard(BoardVO);
        return res;
    }

    @Override
    public int deleteBoard(BoardVO BoardVO) {
        int res = mapper.deleteBoard(BoardVO);
        return res;
    }

}
