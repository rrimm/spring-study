package com.mysite.springmybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemberMapper {

	@Select("select * from tab_mybatis order by id")
	public ArrayList<MemberVO> getMemberlist();

	@Select("select * from tab_mybatis where id = #{id}")
	public MemberVO selectMember(MemberVO memberVO);

	@Insert("insert into tab_mybatis values (#{id}, #{name}, #{email}, #{phone})")
	public void insertMember(MemberVO memberVO);

	@Update("update tab_mybatis set name=#{name}, email=#{email}, phone=#{phone} where id=#{id}")
	public void updateMember(MemberVO memberVO);

	@Delete("delete from tab_mybatis where id = #{id}")
	public void deleteMember(MemberVO memberVO);

}
