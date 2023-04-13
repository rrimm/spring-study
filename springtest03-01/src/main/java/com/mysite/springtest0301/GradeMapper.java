package com.mysite.springtest0301;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper // interface를 매퍼로 등록하기 위해 사용(sql문을 호출하기 위해 사용)
public interface GradeMapper {

	public ArrayList<GradeVO> getGradeList();

	public ArrayList<HashMap<String, String>> getGradeList2();
	
	public ArrayList<GradeDTO> getGradeList3();

	public GradeVO selectGrade(@Param("id") String id);
	
	public GradeVO getGrade(HashMap<String, String> map);	// 여러개의 파라미터를 전달받기위해 HashMap 사용

	public GradeVO getGrade2(HashMap<String, String> map);
	
	public HashMap<String, String> selectGrade2(@Param("id") String id);

	public HashMap<String, String> selectGrade3(@Param("id") String id);

	public GradeDTO selectGrade4(@Param("id") String id);

	public int insertGrade(GradeVO grade);

	public int updateGrade(GradeVO grade);

	public int deleteGrade(@Param("id") String id);

	public int countGrade();

}