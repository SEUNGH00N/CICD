<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.model.User" %>
<%@ page import="com.example.dao.UserDao" %>
<%@ page import="com.example.dao.impl.UserDaoImpl" %>

<%
    // 데이터 액세스 객체 생성
    UserDao userDao = new UserDaoImpl();

    // 파라미터 처리 (새 사용자 추가)
    String action = request.getParameter("action");
    if ("add".equals(action)) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        if (name != null && email != null) {
            User newUser = new User();
            newUser.setName(name);
            newUser.setEmail(email);
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
        <h1>Add New User</h1>
        <form action="addUser.jsp" method="GET">
            <input type="hidden" name="action" value="add" />
            Name: <input type="text" name="name" required /><br/>
            Email: <input type="email" name="email" required /><br/>
            <input type="submit" value="Add User" />
        </form>
        
        <button onclick="location.href='index.jsp'">Back to Main</button>
    </div>
</body>
</html>
