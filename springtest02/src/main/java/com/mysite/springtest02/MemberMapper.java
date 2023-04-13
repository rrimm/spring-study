package com.mysite.springtest02;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper // interface를 매퍼로 등록하기 위해 사용(sql문을 호출하기 위해 사용)
public interface MemberMapper {

	@Select("select * from member order by id")
	public ArrayList<MemberVO> getMembers();

	@Insert("insert into member values(#{id}, #{pw}, #{email})")
	public int insertMember(MemberVO member);
//	@Insert("insert into member values(#{member.id}, #{member.pw}, #{member.email})")
//	int insertMember(@Param("member") MemberVO member);
	
	@Select("select * from member where id=#{id}")
	public MemberVO selectMember(@Param("id") String id);
	
	@Update("update member set pw=#{pw}, email=#{email} where id=#{id}")
	public int updateMember(MemberVO member);
//	public int updateMember(@Param("id") String id, @Param("pw") String pw, @Param("email") String email);

	@Delete("delete from member where id=#{id}")
	public int deleteMember(@Param("id") String id);
	
	@Select("select count(*) from member")
	public int countMember();
	
}