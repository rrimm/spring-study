<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="java.util.*" %>
<%@ page import = "com.mysite.springmemberboard02.member.*" %>

<%

	if ((session.getAttribute("id")==null) || 
	  (!((String)session.getAttribute("id")).equals("admin"))) {
		out.println("<script>");
		out.println("location.href='loginForm.jsp'");
		out.println("</script>");
	}
	
	ArrayList<MemberVO> member_list = (ArrayList<MemberVO>)request.getAttribute("member_list");

%>
<html>
<head>
<title>회원관리 시스템 관리자모드(회원 목록 보기)</title>
</head>
<body>
<center>
<table border=1 width=300>
	<tr align=center><td colspan=3>회원 목록</td></tr>
	<%
	for (int i=0; i<member_list.size(); i++)
	{
		MemberVO vo = (MemberVO)member_list.get(i);
	
%>
	<tr align=center>
		<td>
			<a href="memberinfo.me?id=<%=vo.getId() %>"><%=vo.getId() %></a>
		</td>
		<td><a href="memberdelete.me?id=<%=vo.getId() %>">삭제</a></td>
		<td><a href="updateform.me?id=<%=vo.getId() %>">수정</a></td>
	</tr>
	<%} %>
</table>
</center>
</body>
</html>
