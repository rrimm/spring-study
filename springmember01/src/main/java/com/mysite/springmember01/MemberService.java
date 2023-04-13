package com.mysite.springmember01;

import java.util.ArrayList;

public interface MemberService {
	public ArrayList<MemberVO> getMemberList();
	public int insertMember(MemberVO memberVO);
	public int userCheck(MemberVO memberVO);
	public MemberVO selectMember(MemberVO memberVO);
	public int updateMember(MemberVO memberVO);
	public int deleteMember(MemberVO memberVO);
	public int countMember();
}

