package com.mysite.springmember01;

import java.io.PrintWriter;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // @Controller: 뷰 반환
public class MemberController {

	private MemberService memberService; // private MemberServiceImpl memberService

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/")
	public String startPage() {
		return "loginForm";
	}

	@RequestMapping("/loginform.me")
	public String loginForm() {
		return "loginForm";
	}

	@RequestMapping("/joinform.me")
	public String joinForm() {
		return "joinForm";
	}

	@RequestMapping("/joinprocess.me")
	public void insertMember(MemberVO memberVO, HttpServletResponse response) throws Exception {
		int res = memberService.insertMember(memberVO);

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		if (res == 1) {
			writer.write("<script>alert('회원 가입 성공!!');" + "location.href='./loginform.me';</script>");
		} else {
			writer.write("<script>alert('회원 가입 실패!!');" + "location.href='./loginform.me';</script>");
		}
	}

	@RequestMapping("/login.me")
	public void userCheck(MemberVO memberVO, HttpSession session, HttpServletResponse response) throws Exception {
		int res = memberService.userCheck(memberVO);
		log.info("res: " + res);

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		if (res == 1) {
			session.setAttribute("id", memberVO.getId());
			System.out.println("session.getAttribute(id)" + session.getAttribute("id"));
			writer.write("<script>alert('로그인 성공!!'); location.href='./main.me';</script>");
		} else {
			writer.write("<script>alert('로그인 실패!!');location.href='./loginform.me';</script>");
		}

	}

	@RequestMapping("/main.me")
	public String mainPage() { // view를 리턴하므로 string 타입
		return "main";
	}

//	@RequestMapping("/memberlist.me")
//	public String getMemberList(Model model) {	// Model: 뷰에게 전달할 데이터를 저장. 뷰에서는 request.getAttribute("member_list")로 데이터를 가져옴
//		ArrayList<MemberVO> member_list = memberService.getMemberList();
//		model.addAttribute("member_list", member_list);
//		return "member_list";
//	}
	@RequestMapping("/memberlist.me")
	public ModelAndView getMemberList(ModelAndView mv) {	// Model: 뷰에게 전달할 데이터를 저장. 뷰에서는 request.getAttribute("member_list")로 데이터를 가져옴
		ArrayList<MemberVO> member_list = memberService.getMemberList();
		mv.addObject("member_list", member_list);
		mv.setViewName("member_list");
		
		return mv;
	}
	
	@RequestMapping("/memberinfo.me")
	public String selectMember(MemberVO memberVO, Model model) {	// Model: 뷰에게 전달할 데이터를 저장. 뷰에서는 request.getAttribute("member_list")로 데이터를 가져옴
		MemberVO vo = memberService.selectMember(memberVO);
		model.addAttribute("memberVO", vo);
		
		return "member_info";
	}
	
	@RequestMapping("/updateform.me")
	public String updateForm(MemberVO memberVO, Model model) {	// Model: 뷰에게 전달할 데이터를 저장. 뷰에서는 request.getAttribute("member_list")로 데이터를 가져옴
		MemberVO vo = memberService.selectMember(memberVO);
		model.addAttribute("memberVO", vo);
		
		return "member_updateForm";
	}
	
	@RequestMapping("/update.me")
	public String updateMember(MemberVO memberVO) {	// Model: 뷰에게 전달할 데이터를 저장. 뷰에서는 request.getAttribute("member_list")로 데이터를 가져옴
		memberService.updateMember(memberVO);
		
		return "redirect:/memberlist.me";	//memberlist.me로 바로 이동
	}
	
	@RequestMapping("/memberdelete.me")
	public String deleteMember(MemberVO memberVO) {	// Model: 뷰에게 전달할 데이터를 저장. 뷰에서는 request.getAttribute("member_list")로 데이터를 가져옴
		memberService.deleteMember(memberVO);
		
		return "redirect:/memberlist.me";	//memberlist.me로 바로 이동
	}
	
//	@GetMapping("/getMemberList")
//	ArrayList<MemberVO> getMemberList() {
//		ArrayList<MemberVO> memberlist = memberService.getMemberList();
//		return memberlist;
//	}
//
//	@PostMapping("/insertMember")
//	public int insertMember(MemberVO member) {
//		// new MemberVO("qqq", "333");
//		MemberVO vo = MemberVO.builder().id("qqq").password("333").build();
//		log.info(vo.getId());
//		int res = memberServiceImpl.insertMember(member);
//		return res;
//	}
//
//	@GetMapping("/selectMember")
//	public MemberVO selectMember(@RequestParam String id) { // 파라미터 이름과 변수 이름이 동일한 경우에만 적용 가능
//		MemberVO vo = memberServiceImpl.selectMember(id);
//		return vo;
//	}
//
//	@GetMapping("/userCheck")
//	public int userCheck(@RequestParam("id") String id, @RequestParam("password") String password) {
////		HashMap<String, String> map = new HashMap<String, String>();
////		map.put("id", id);
////		map.put("password", password);
//		int res = memberServiceImpl.userCheck(member);
//		return res;
//	}
//
//	@PostMapping("/updateMember")
//	public int updateMember(MemberVO member) {
//		int res = memberServiceImpl.updateMember(member);
//		return res;
//	}
//
//	@PostMapping("/deleteMember")
//	public int deleteMember(String id) {
//		int res = memberServiceImpl.deleteMember(id);
//		return res;
//	}
//
//	@GetMapping("/countMember")
//	public int countMember() {
//		int res = memberServiceImpl.countMember();
//		return res;
//	}

//	@PostMapping("/updateMember")
//	public int updateMember(@RequestParam String id, @RequestParam String pw, @RequestParam String email) {	// 파라미터 이름과 변수 이름이 동일한 경우에만 적용 가능
//		int res = memberServiceImpl.updateMember(id, pw, email);
//		return res;
//	}

}