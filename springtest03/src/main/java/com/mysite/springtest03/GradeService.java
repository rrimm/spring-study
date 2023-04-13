package com.mysite.springtest03;

import java.util.ArrayList;
import java.util.HashMap;

public interface GradeService {
	public ArrayList<GradeVO> getGradeList();
	public ArrayList<HashMap<String, String>> getGradeList2();
	public GradeVO selectGrade(String id);
	public HashMap<String, String> selectGradeData(String id);
	public int insertGrade(GradeVO grade);
	public int updateGrade(GradeVO grade);
	public int deleteGrade(String id);
	public int countGrade();
}

