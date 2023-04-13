package com.mysite.reactspring;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController {
    private BoardService BoardService;// private BoardServiceImpl BoardService

    public BoardController(BoardService BoardService) { // BoardServiceImpl BoardService
        this.BoardService = BoardService;
    }


    @RequestMapping("/list")
    public List<BoardVO> getBoardList(BoardVO vo) {
        List<BoardVO> boardlist = BoardService.getBoardList();
        return boardlist;
    }

    @RequestMapping("/insert")
    public int insertBoard(@RequestBody BoardVO vo) {   //json 기반의 메시지를 사용하는 요청의 경우 사용
        int res = BoardService.insertBoard(vo);
        return res;
    }

    @RequestMapping("/detail")
    public BoardVO getDetail(@RequestBody BoardVO vo) {
        BoardVO boardVO = BoardService.getDetail(vo);
        return boardVO;
    }

    @RequestMapping("/update")
    public int updateBoard(@RequestBody BoardVO vo) {
        int res = BoardService.updateBoard(vo);
        return res;
    }

    @RequestMapping("/delete")
    public int deleteBoard(@RequestBody BoardVO vo) {
        int res = BoardService.deleteBoard(vo);

        return res;
    }
}
