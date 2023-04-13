package com.mysite.springmemberboard02.member;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	private MemberMapper mapper;
	
	public MemberServiceImpl(MemberMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public int insertMember(MemberVO memberVO) {
		int res = mapper.insertMember(memberVO);
		return res;
	}

	@Override
	public int userCheck(MemberVO memberVO) {
		int res = mapper.userCheck(memberVO);
		return res;
	}

	@Override
	public ArrayList<MemberVO> getMemberlist() {
		ArrayList<MemberVO> member_list = new ArrayList<MemberVO>();
		member_list = mapper.getMemberlist();
		return member_list;
	}

	@Override
	public MemberVO selectMember(MemberVO memberVO) {
		MemberVO vo  = mapper.selectMember(memberVO);
		return vo;
	}

	@Override
	public int updateMember(MemberVO memberVO) {
		int res = mapper.updateMember(memberVO);
		return res;
	}

	@Override
	public int deleteMember(MemberVO memberVO) {
		int res = mapper.deleteMember(memberVO); 
		return res;
	}

}
