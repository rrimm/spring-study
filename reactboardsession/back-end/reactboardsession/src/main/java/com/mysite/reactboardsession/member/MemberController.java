package com.mysite.reactboardsession.member;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
	
	private MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// @RequestBody : json기반의 메시지를 사용하는 요청의 경우 사용함
	@RequestMapping("/login") 
	public int login(@RequestBody MemberVO vo) throws Exception {
		int res = memberService.login(vo);
	
		return res;
	} 
	
	@RequestMapping("/insertMember")
	public int insertMember(@RequestBody MemberVO vo) throws Exception {
		int res = memberService.insertMember(vo);
	
		return res;
	} 

}
