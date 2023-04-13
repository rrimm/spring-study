package com.mysite.springtest03;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController	
public class GradeController {	// HTTP 요청을 처리하는 REST API를 구현한 클래스
	GradeServiceImpl gradeServiceImpl;

	public GradeController(GradeServiceImpl gradeServiceImpl) {
		this.gradeServiceImpl = gradeServiceImpl;
	}

	@GetMapping("/getGradeList")
//	@RequestMapping(value="/getGradeList", method="RequestMethod.GET)
	public ArrayList<GradeVO> getGradeList() {
		ArrayList<GradeVO> gradelist = gradeServiceImpl.getGradeList();
		return gradelist;
	}
	@GetMapping("/getGradeList2")
	public ArrayList<HashMap<String, String>> getGradeList2() {
		ArrayList<HashMap<String, String>> gradelist = gradeServiceImpl.getGradeList2();
		return gradelist;
	}
	
	@GetMapping("/selectGrade")
	public GradeVO selectGrade(@RequestParam String id) { 
		GradeVO vo = gradeServiceImpl.selectGrade(id);
		return vo;
	}
	
	@GetMapping("/selectGradeData")
	public HashMap<String, String> selectGradeData(String id) {
		HashMap<String, String> vo = gradeServiceImpl.selectGradeData(id);
		return vo;
	}
	
	@PostMapping("/insertGrade")
//	@RequestMapping(value="/insertGrade", method="RequestMethod.POST)
	public int insertGrade(GradeVO grade) {
		int res = gradeServiceImpl.insertGrade(grade);
		return res;
	}

	@PostMapping("/updateGrade")
	public int updateGrade(GradeVO grade) {
		System.out.println("updateGrade1: "+ grade);
		int res = gradeServiceImpl.updateGrade(grade);
		return res;
	}

	@PostMapping("/deleteGrade")
	public int deleteGrade(@RequestParam("id") String id) {
		int res = gradeServiceImpl.deleteGrade(id);
		return res;
	}

	@GetMapping("/countGrade")
	public int countGrade() {
		int res = gradeServiceImpl.countGrade();
		return res;
	}

}