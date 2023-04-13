package com.mysite.springtest0301;

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
	public ArrayList<GradeDTO> getGradeList3() {
		ArrayList<GradeDTO> gradelist = mapper.getGradeList3();
		return gradelist;
	}

	@Override
	public int insertGrade(GradeVO grade) {
		grade.processGrade();
		int res = mapper.insertGrade(grade);
		return res;
	}
	
	@Override
	public GradeVO getGrade(HashMap<String, String> map){
		GradeVO vo = mapper.getGrade(map);
		return vo;
	}
	
	@Override
	public GradeVO getGrade2(String id, String name){
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("id", id);
		map.put("name", name);
		GradeVO vo = mapper.getGrade(map);
		return vo;
	}
	
	@Override
	public GradeVO selectGrade(String id) {
		GradeVO vo = mapper.selectGrade(id);
		return vo;
	}

	@Override
	public HashMap<String, String> selectGrade2(String id) {
		HashMap<String, String> vo = mapper.selectGrade2(id);
		return vo;
	}
	
	@Override
	public HashMap<String, String> selectGrade3(String id) {
		HashMap<String, String> vo = mapper.selectGrade3(id);
		return vo;
	}
	
	@Override
	public GradeDTO selectGrade4(String id) {
		GradeDTO vo = mapper.selectGrade4(id);
		return vo;
	}
	
	@Override
	public int updateGrade(GradeVO grade) {
		grade.processGrade();
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
