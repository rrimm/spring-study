package com.mysite.springmember01;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper // interface를 매퍼로 등록하기 위해 사용(sql문을 호출하기 위해 사용)
public interface MemberMapper {

	@Insert("insert into membertbl values(#{id}, #{password}, #{name}, #{age}, #{gender},#{email} )")
	public int insertMember(@Param("memberVO") MemberVO memberVO);
	
	@Select("select * from membertbl order by id")
	public ArrayList<MemberVO> getMemberList();

	@Select("select count(*) from membertbl where id=#{id} and password=#{password})")
	public int userCheck(MemberVO memberVO);
	
	@Select("select * from membertbl where id=#{id}")
	public MemberVO selectMember(MemberVO memberVO);

	@Update("update membertbl set password=#{password}, name=#{name}, age=#{age}, gender=#{gender}, email=#{email} where id=#{id}")
	public int updateMember(MemberVO memberVO);

	@Delete("delete from membertbl where id=#{id}")
	public int deleteMember(MemberVO memberVO);

	@Select("select count(*) from membertbl")
	public int countMember();

}