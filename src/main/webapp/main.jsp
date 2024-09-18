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
            <% 
                User loggedInUser = (User) session.getAttribute("loggedInUser");
                if (loggedInUser != null) {
                    out.println("<p>Hello, " + loggedInUser.getName() + "!</p>");
                    out.println("<p>Your email: " + loggedInUser.getEmail() + "</p>");
                } else {
                    out.println("<p>You are not logged in. <a href='index.jsp'>Login</a></p>");
                }
            %>
        </div>
    </div>
</body>
</html>
