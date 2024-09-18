<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.dao.UserDao"%>
<%@ page import="com.example.dao.impl.UserDaoImpl"%>
<%@ page import="com.example.model.User"%>

<%
    // 데이터 액세스 객체 생성
    UserDao userDao = new UserDaoImpl();

    // 로그인 처리
    String action = request.getParameter("action");
    if ("login".equals(action)) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email != null && password != null) {
            User user = userDao.findByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                session.setAttribute("loggedInUser", user);
                response.sendRedirect("main.jsp"); // 로그인 후 리다이렉트
            } else {
                request.setAttribute("errorMessage", "Invalid email or password");
            }
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
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
        <!-- 오른쪽 패널: 로그인 폼 -->
        <div class="content">
            <h1>Login</h1>
            <% if (request.getAttribute("errorMessage") != null) { %>
                <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
            <% } %>
            <form action="index.jsp" method="GET">
                <input type="hidden" name="action" value="login" />
                Email: <input type="email" name="email" required /><br />
                Password: <input type="password" name="password" required /><br />
                <input type="submit" value="Login" />
            </form>
        </div>
    </div>
</body>
</html>
