package com.mysite.springtest03;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper // interface를 매퍼로 등록하기 위해 사용(sql문을 호출하기 위해 사용)
public interface GradeMapper {

	@Select("select * from grade order by id")
	public ArrayList<GradeVO> getGradeList();
	
	@Select("select id, name, grade from grade order by id")
	public ArrayList<HashMap<String, String>> getGradeList2();

	@Select("select * from grade where id=#{id}")
	public GradeVO selectGrade(@Param("id") String id);
	
	@Select("select id, name, grade from grade where id=#{id}")
	public HashMap<String, String> selectGradeData(@Param("id") String id);
	
	@Insert("insert into grade values(#{id}, #{name}, #{kor}, #{eng}, #{math}, #{tot}, #{avg},#{grade})")
	public int insertGrade(GradeVO grade);

	@Update("update grade set kor=#{kor}, eng=#{eng}, math=#{math}, tot=#{tot}, avg=#{avg}, grade=#{grade} where id=#{id}")
	public int updateGrade(GradeVO grade);

	@Delete("delete from grade where id=#{id}")
	public int deleteGrade(@Param("id") String id);
	
	@Select("select count(*) from grade")
	public int countGrade();
	
}
