package com.mysite.springtest03;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {
	// 생성자 방식
	GradeMapper mapper;

	public GradeServiceImpl(GradeMapper mapper) { // 생성자
		this.mapper = mapper;
	}

	@Override
	public ArrayList<GradeVO> getGradeList() {
		ArrayList<GradeVO> gradelist = mapper.getGradeList();
		return gradelist;
	}

	@Override
	public ArrayList<HashMap<String, String>> getGradeList2() {
		ArrayList<HashMap<String, String>> gradelist = mapper.getGradeList2();
		return gradelist;
	}

	@Override
	public GradeVO selectGrade(String id) {
		GradeVO vo = mapper.selectGrade(id);
		return vo;
	}

	@Override
	public HashMap<String, String> selectGradeData(String id) {
		HashMap<String, String> vo = mapper.selectGradeData(id);
		return vo;
	}

	@Override
	public int insertGrade(GradeVO grade) {
		grade.processGrade();
		int res = mapper.insertGrade(grade);
		return res;
	}

	@Override
	public int updateGrade(GradeVO grade) {
		grade.processGrade();
		System.out.println("updateGrade2: " + grade);
		int res = mapper.updateGrade(grade);
		return res;
	}

	@Override
	public int deleteGrade(String id) {
		int res = mapper.deleteGrade(id);
		return res;
	}

	@Override
	public int countGrade() {
		int res = mapper.countGrade();
		return res;
	}
}
