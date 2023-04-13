<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mysite.springmybatis.MemberVO"%>

<%
ArrayList<MemberVO> memberlist = (ArrayList<MemberVO>) request.getAttribute("memberlist");

%>
<html>
<head>
<title>회원관리</title>
<script language="javascript">
function res(){
	location.href="memberlist.me";
}

function updateform(id){
	location.href="updateform.me?id="+id;
}

function delete(id){
	location.href="delete.me?id="+id;
}

</script>
</head>
<body>
	<center>
		<form action="insert.me" method="post">
			<table border=1 align=center>
				<tr align=center>
					<td>아이디</td>
					<td>이름</td>
					<td>이메일</td>
					<td>전화번호</td>
					<td><input type="button" value="리스트" onclick="res()"></td>
				</tr>
				<tr align=center>

					<td><input type="text" name="id" id="id"></td>
					<td><input type="text" name="name" id="name"></td>
					<td><input type="text" name="email" id="email" /></td>
					<td><input type="text" name="phone" id="phone" /></td>
					<td colspan="2"><input type="submit" value="추가"></td>

				</tr>
				<%
				for (int i = 0; i < memberlist.size(); i++) {
					MemberVO vo = (MemberVO) memberlist.get(i);
				%>
				<tr align=center>
					<td><%=vo.getId()%></td>
					<td><%=vo.getName()%></td>
					<td><%=vo.getEmail()%></td>
					<td><%=vo.getPhone()%></td>
					<td>	<a href="delete.me?id=<%=vo.getId()%>">삭제</a> <a
						href="updateform.me?id=<%=vo.getId()%>">수정</a></td>
				</tr>
				<%
				}
				%>
			</table>
		</form>
	</center>
</body>
</html>
