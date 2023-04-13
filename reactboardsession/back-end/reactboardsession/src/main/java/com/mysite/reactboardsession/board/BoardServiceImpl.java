package com.mysite.reactboardsession.board;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("boardService")
public class BoardServiceImpl implements BoardService {

    private BoardMapper mapper;

    public BoardServiceImpl(BoardMapper mapper) {

        this.mapper = mapper;
    }

    @Override
    public List<BoardVO> getBoardList(int page_num, int limit) {
        int start = (page_num - 1) * limit;
        log.info("start:" + start);
        log.info("page_num:" + page_num);
        List<BoardVO> boardlist = mapper.getBoardList(start, limit);
        return boardlist;
    }

    @Override
    public int getCount() {
        int res = mapper.getCount();
        return res;
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
