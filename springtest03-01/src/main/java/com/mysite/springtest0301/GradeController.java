package com.mysite.springtest0301;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GradeController {

	GradeServiceImpl gradeServiceImpl;

	public GradeController(GradeServiceImpl gradeServiceImpl) {
		this.gradeServiceImpl = gradeServiceImpl;
	}

	@GetMapping("/getGradeList")
	ArrayList<GradeVO> getGradeList() {
		ArrayList<GradeVO> gradelist = gradeServiceImpl.getGradeList();

		return gradelist;
	}
	
	@GetMapping("/getGradeList2")
	ArrayList<HashMap<String, String>> getGradeList2() {
		ArrayList<HashMap<String, String>> gradelist = gradeServiceImpl.getGradeList2();

		return gradelist;
	}
	
	@GetMapping("/getGradeList3")
	ArrayList<GradeDTO> getGradeList3() {
		ArrayList<GradeDTO> gradelist = gradeServiceImpl.getGradeList3();

		return gradelist;
	}
	
	@GetMapping("/getGrade")
	public GradeVO getGrade(@RequestParam("id") String id, @RequestParam("name") String name){
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("id", id);
		map.put("name", name);
		GradeVO vo = gradeServiceImpl.getGrade(map);
		return vo;
	}
	
	@GetMapping("/getGrade2")
	public GradeVO getGrade2(@RequestParam("id") String id, @RequestParam("name") String name){
		GradeVO vo = gradeServiceImpl.getGrade2(id, name);
		return vo;
	}
	
	@GetMapping("/selectGrade")
	public GradeVO selectGrade(@RequestParam String id) { // 파라미터 이름과 변수 이름이 동일한 경우에만 적용 가능
		GradeVO vo = gradeServiceImpl.selectGrade(id);
		return vo;
	}

	@GetMapping("/selectGrade2")
	public HashMap<String, String> selectGrade2(String id) {
		HashMap<String, String> vo = gradeServiceImpl.selectGrade2(id);
		return vo;
	}
	
	@GetMapping("/selectGrade3")
	public HashMap<String, String> selectGrade3(String id) {
		HashMap<String, String> vo = gradeServiceImpl.selectGrade3(id);
		return vo;
	}
	
	@GetMapping("/selectGrade4")
	public GradeDTO selectGrade4(String id) {
		GradeDTO vo = gradeServiceImpl.selectGrade4(id);
		return vo;
	}

	@PostMapping("/insertGrade")
	public int insertGrade(GradeVO grade) {
		// new MemberVO("qqq", "333");
//		GradeVO vo = GradeVO.builder().id("qqq").pw("333").build();
//		System.out.println(vo.getId());
//		System.out.println(vo.getPw());
//		System.out.println(vo.getEmail());
//		System.out.println(vo);
//		log.info(vo.getId());
		int res = gradeServiceImpl.insertGrade(grade);

		return res;
	}

	@PostMapping("/updateGrade")
	public int updateGrade(GradeVO grade) {
		int res = gradeServiceImpl.updateGrade(grade);
		return res;
	}

	@PostMapping("/deleteGrade")
	public int deleteGrade(String id) {
		int res = gradeServiceImpl.deleteGrade(id);
		return res;
	}

	@GetMapping("/countGrade")
	public int countGrade() {
		int res = gradeServiceImpl.countGrade();
		return res;
	}

}