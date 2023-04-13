package com.mysite.springtest0301;

import java.util.ArrayList;
import java.util.HashMap;

public interface GradeService {
	public ArrayList<GradeVO> getGradeList();
	public ArrayList<HashMap<String, String>> getGradeList2();
	public ArrayList<GradeDTO> getGradeList3();
	int insertGrade(GradeVO grade);
	public GradeVO getGrade(HashMap<String, String> map);
	public GradeVO getGrade2(String id, String name);
	public GradeVO selectGrade(String id);
	public HashMap<String, String> selectGrade2(String id);
	public HashMap<String, String> selectGrade3(String id);
	public GradeDTO selectGrade4(String id);
	public int updateGrade(GradeVO grade);
	public int deleteGrade(String id);
	public int countGrade();
}

