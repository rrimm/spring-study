package com.mysite.springtest02;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

	MemberServiceImpl memberServiceImpl;

	public MemberController(MemberServiceImpl memberServiceImpl) {
		this.memberServiceImpl = memberServiceImpl;
	}

	@GetMapping("/getMembers")
//	@RequestMapping(value="/getMembers", method="RequestMethod.GET)
	ArrayList<MemberVO> getMembers() {
		ArrayList<MemberVO> memberlist = memberServiceImpl.getMembers();

		return memberlist;
	}

	@PostMapping("/insertMember")
	public int insertMember(MemberVO member) {
		// new MemberVO("qqq", "333");
		MemberVO vo=MemberVO.builder()
				.id("qqq")
				.pw("333")
				.build();
		System.out.println(vo.getId());
		System.out.println(vo.getPw());
		System.out.println(vo.getEmail());
		System.out.println(vo);
		int res = memberServiceImpl.insertMember(member);

		return res;
	}

	@GetMapping("/selectMember")
	public MemberVO selectMember(@RequestParam String id) {	// 파라미터 이름과 변수 이름이 동일한 경우에만 적용 가능
		MemberVO vo = memberServiceImpl.selectMember(id);
		return vo;
	}
	
	@PostMapping("/updateMember")
	public int updateMember(MemberVO member) {	
		int res = memberServiceImpl.updateMember(member);
		return res;
	}
	
	@PostMapping("/deleteMember")
	public int deleteMember(String id) {	
		int res = memberServiceImpl.deleteMember(id);
		return res;
	}
	
	@GetMapping("/countMember")
	public int countMember() {
		int res=memberServiceImpl.countMember();
		return res;
	}
	
//	@PostMapping("/updateMember")
//	public int updateMember(@RequestParam String id, @RequestParam String pw, @RequestParam String email) {	// 파라미터 이름과 변수 이름이 동일한 경우에만 적용 가능
//		int res = memberServiceImpl.updateMember(id, pw, email);
//		return res;
//	}
	
}