package com.mysite.reactboardsession.board;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("select board_num, board_writer, board_title, board_content, date_format(board_date, '%y-%m-%d') as board_date from board_tbl order by board_num desc limit #{start}, #{limit}")
    public List<BoardVO> getBoardList(@Param("start") int start, @Param("limit") int limit);    // 리스트 출력

    @Select("select count(*) as count from board_tbl")
    public int getCount();

    @Insert("insert into board_tbl(board_writer, board_title, board_content) values (#{board_writer}, #{board_title}, #{board_content})")
    public int insertBoard(BoardVO board); // 글쓰기

    @Select("select board_num, board_writer, board_title, board_content, date_format(board_date, '%y-%m-%d') as board_date from board_tbl where board_num=#{board_num}")
    public BoardVO getDetail(BoardVO board); // 글조회

    @Update("update board_tbl set board_title=#{board_title}, board_content=#{board_content}, board_date=now() where board_num=#{board_num}")
    public int updateBoard(BoardVO board);

    @Delete("delete from board_tbl where board_num=#{board_num}")
    public int deleteBoard(BoardVO board);

}
