<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.model.User"%>
<%@ page import="com.example.dao.UserDao"%>
<%@ page import="com.example.dao.impl.UserDaoImpl"%>

<%
    // 데이터 액세스 객체 생성
    UserDao userDao = new UserDaoImpl();

    // 사용자 ID를 파라미터로 받아서 사용자 정보 가져오기
    String userIdParam = request.getParameter("id");
    Long userId = userIdParam != null ? Long.parseLong(userIdParam) : null;
    User user = userId != null ? userDao.findById(userId) : null;

    // 폼 제출 처리
    String action = request.getParameter("action");
    if ("update".equals(action)) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password); // Assuming you want to update the password as well
            userDao.update(user);
            response.sendRedirect("userList.jsp"); // 업데이트 후 사용자 목록으로 리다이렉트
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update User</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <!-- 왼쪽 패널: 버튼 그룹 -->
        <div class="sidebar">
            <h2>Navigation</h2>
            <a href="index.jsp">Home</a>
            <a href="userList.jsp">User List</a>
            <a href="addUser.jsp">Add New User</a>
        </div>
        <!-- 오른쪽 패널: 사용자 업데이트 폼 -->
        <div class="content">
            <h1>Update User</h1>
            <% if (user != null) { %>
                <form action="updateUser.jsp" method="GET">
                    <input type="hidden" name="id" value="<%=user.getId()%>" />
                    <input type="hidden" name="action" value="update" />
                    Name: <input type="text" name="name" value="<%=user.getName()%>" required /><br />
                    Email: <input type="email" name="email" value="<%=user.getEmail()%>" required /><br />
                    Password: <input type="password" name="password" value="<%=user.getPassword()%>" required /><br />
                    <input type="submit" value="Update User" />
                </form>
            <% } else { %>
                <p>User not found.</p>
            <% } %>
        </div>
    </div>
</body>
</html>
