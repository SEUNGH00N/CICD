<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.example.model.User"%>
<%@ page import="com.example.dao.UserDao"%>
<%@ page import="com.example.dao.impl.UserDaoImpl"%>

<%
    // 데이터 액세스 객체 생성
    UserDao userDao = new UserDaoImpl();

    // 파라미터 처리 (새 사용자 추가)
    String action = request.getParameter("action");
    if ("add".equals(action)) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password"); // Get password from request
        if (name != null && email != null && password != null) {
            User newUser = new User();
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setPassword(password); // Set password
            userDao.save(newUser);
            response.sendRedirect("userList.jsp"); // 사용자 목록으로 리다이렉트
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add User</title>
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
        <!-- 오른쪽 패널: 사용자 추가 폼 -->
        <div class="content">
            <h1>Add New User</h1>
            <form action="addUser.jsp" method="GET">
                <input type="hidden" name="action" value="add" /> 
                Name: <input type="text" name="name" required /><br /> 
                Email: <input type="email" name="email" required /><br /> 
                Password: <input type="password" name="password" required /><br /> <!-- Added password field -->
                <input type="submit" value="Add User" />
            </form>
        </div>
    </div>
</body>
</html>
