<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.User" %>
<%@ page import="com.example.dao.UserDao" %>
<%@ page import="com.example.dao.impl.UserDaoImpl" %>

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
                <td><%= user.getId() %></td>
                <td><%= user.getName() %></td>
                <td><%= user.getEmail() %></td>
                <td>
                    <a href="userList.jsp?action=delete&id=<%= user.getId() %>">Delete</a>
                    <a href="updateUser.jsp?id=<%= user.getId() %>">Update</a>
                </td>
            </tr>
            <%
                }
            %>
        </table>

        <h2>Add New User</h2>
        <form action="addUser.jsp" method="GET">
            <input type="submit" value="Go to Add User Page" />
        </form>

        <button onclick="location.href='index.jsp'">Back to Main</button>
    </div>
</body>
</html>
