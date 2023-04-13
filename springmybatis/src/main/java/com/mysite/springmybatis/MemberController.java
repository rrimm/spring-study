package com.mysite.springmybatis;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // 기본적으로 뷰(view)를 반환한다.
public class MemberController {
	private MemberService memberService;// private MemberServiceImpl memberService

	public MemberController(MemberService memberService) { // MemberServiceImpl memberService
		this.memberService = memberService;
	}

	@RequestMapping("/")
	public String startPage() {
		return "redirect:/memberlist.me";
	}

	@RequestMapping("/memberlist.me")
	public ModelAndView getMemberlist(ModelAndView mv) {
		List<MemberVO> memberlist = memberService.getMemberlist();
		mv.addObject("memberlist", memberlist);
		mv.setViewName("memberlist");

		return mv;
	}

	@RequestMapping("/insert.me")
	public ModelAndView insertMember(MemberVO memberVO, ModelAndView mv) {
		memberService.insertMember(memberVO);
		List<MemberVO> memberlist = memberService.getMemberlist();
		mv.addObject("memberlist", memberlist);
		mv.setViewName("memberlist");

		return mv;

	}

	@RequestMapping("/updateform.me")
	public String updateForm(MemberVO memberVO, Model model) {
		MemberVO vo = memberService.selectMember(memberVO);
		model.addAttribute("memberVO", vo);

		return "updateForm";
	}

	@RequestMapping("/update.me")
	public String updateMember(MemberVO memberVO) {
		memberService.updateMember(memberVO);
		return "redirect:/memberlist.me";
	}

	@RequestMapping("/delete.me")
	public String deleteMember(MemberVO memberVO) {
		memberService.deleteMember(memberVO);

		return "redirect:/memberlist.me";
	}
}
