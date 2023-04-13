<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="com.mysite.springmember01.MemberVO"%>
<%
if ((session.getAttribute("id") == null) || (!((String) session.getAttribute("id")).equals("admin"))) {
	out.println("<script>");
	out.println("location.href='loginForm.jsp'");
	out.println("</script>");
}

MemberVO vo = (MemberVO) request.getAttribute("memberVO");
%>
<html>
<head>
<title>회원관리 시스템 관리자모드(회원 정보 수정)</title>
</head>
<body>
	<center>
		<form name="updateform" action="update.me" method="post">
			<table border=1 width=400>
				<tr>
					<td>아이디 :</td>
					<td><%=vo.getId()%></td>
					<input type="hidden" name="id" value=<%=vo.getId()%> />
				</tr>
				<tr>
					<td>비밀번호 :</td>
					<td><input type="password" name="password"
						value=<%=vo.getPassword()%> /></td>
				</tr>
				<tr>
					<td>이름 :</td>
					<td><input type="text" name="name" value=<%=vo.getName()%> /></td>
				</tr>
				<tr>
					<td>나이 :</td>
					<td><input type="text" name="age" value=<%=vo.getAge()%> /></td>
				</tr>
				<tr>
					<td>성별 :</td>
					<td>
						<%
						if (vo.getGender().equals("남")) {
						%> <input type="radio" name="gender" value="남" checked />남자 <input
						type="radio" name="gender" value="여" />여자 <%
 } else {
 %> <input type="radio" name="gender" value="남" />남자 <input
						type="radio" name="gender" value="여" checked />여자 <%
 }
 %>
					</td>
				</tr>
				<tr>
					<td>이메일 주소 :</td>
					<td><input type="text" name="email" size=30
						value=<%=vo.getEmail()%> /></td>
				</tr>
				<tr align=center>
					<td colspan="2"><a href="javascript:updateform.submit()">수정</a>&nbsp;&nbsp;
						<a href="memberlist.me">리스트로 돌아가기</a></td>
				</tr>
			</table>
	</center>
</body>
</html>