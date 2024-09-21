<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.model.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        // 로그인 상태 확인
        if (loggedInUser == null) {
            response.sendRedirect("index.jsp"); // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
            return; // 이후 코드 실행 중단
        }
    %>

    <div class="container">
        <!-- 왼쪽 패널: 버튼 그룹 -->
        <div class="sidebar">
            <h2>Navigation</h2>
            <a href="index.jsp">Home</a>
            <a href="userList.jsp">User List</a>
            <a href="addUser.jsp">Add New User</a>
            <a href="logout.jsp">Logout</a>
        </div>
        <!-- 오른쪽 패널: 성공 페이지 내용 -->
        <div class="content">
            <h1>Welcome</h1>
            <p>Hello, <%= loggedInUser.getName() %>!</p>
            <p>Your email: <%= loggedInUser.getEmail() %></p>
        </div>
    </div>
</body>
</html>
