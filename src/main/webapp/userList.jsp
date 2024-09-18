<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.example.model.User"%>
<%@ page import="com.example.dao.UserDao"%>
<%@ page import="com.example.dao.impl.UserDaoImpl"%>

<%
// 데이터 액세스 객체 생성
UserDao userDao = new UserDaoImpl();

// GET 요청으로 전달된 사용자 목록 가져오기
List<User> userList = userDao.findAll();

// 파라미터 처리 (삭제)
String action = request.getParameter("action");
if ("delete".equals(action)) {
	Long id = Long.parseLong(request.getParameter("id"));
	userDao.delete(id);

	// 목록 업데이트
	userList = userDao.findAll();
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User List</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="container">
		<!-- 왼쪽 패널: 버튼 그룹 -->
		<div class="sidebar">
			<h2>Navigation</h2>
			<a href="index.jsp">Home</a> <a href="userList.jsp">User List</a> <a
				href="addUser.jsp">Add New User</a>
		</div>

		<!-- 오른쪽 패널: 사용자 목록 -->
		<div class="content">
			<h1>User List</h1>
			<table>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Actions</th>
				</tr>
				<%
				for (User user : userList) {
				%>
				<tr>
					<td><%=user.getId()%></td>
					<td><%=user.getName()%></td>
					<td><%=user.getEmail()%></td>
					<td><a
						href="userList.jsp?action=delete&id=<%=user.getId()%>">Delete</a>
						<a href="updateUser.jsp?id=<%=user.getId()%>">Update</a></td>
				</tr>
				<%
				}
				%>
			</table>
		</div>
	</div>
</body>
</html>
