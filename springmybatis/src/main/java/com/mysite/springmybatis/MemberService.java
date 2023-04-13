package com.mysite.springmybatis;

import java.util.ArrayList;

public interface MemberService {
	public void insertMember(MemberVO memberVO);
	public ArrayList<MemberVO> getMemberlist();
	public MemberVO selectMember(MemberVO memberVO);
	public void updateMember(MemberVO memberVO);
	public void deleteMember(MemberVO memberVO);
}
