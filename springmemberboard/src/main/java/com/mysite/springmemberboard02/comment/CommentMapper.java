package com.mysite.springmemberboard02.comment;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CommentMapper {
	
	@Select("select count(*) from table_comment where bno=#{bno}")
    public int commentCount(int bno) throws Exception; // 댓글 개수
	
	@Select("select * from table_comment where bno=#{bno} order by cno desc")
    public List<CommentVO> commentList(int bno) throws Exception; // 댓글 목록
	
	@Insert("insert into table_comment (cno, bno, content, writer, reg_date)"
			+ " values(null,#{bno},#{content},#{writer},now())")
    public int commentInsert(CommentVO comment) throws Exception; // 댓글 저장
	
	@Update("update table_comment set content=#{content} where cno=#{cno}")
    public int commentUpdate(CommentVO comment) throws Exception; // 댓글 수정
	
	@Delete("delete from table_comment where cno = #{cno}")
    public int commentDelete(int cno) throws Exception; // 댓글 삭제

}
