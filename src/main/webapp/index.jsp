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

    // 파라미터 처리 (새 사용자 추가 또는 삭제)
    String action = request.getParameter("action");
    if ("add".equals(action)) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        if (name != null && email != null) {
            User newUser = new User();
            newUser.setName(name);
            newUser.setEmail(email);
            userDao.save(newUser);
        }
    } else if ("delete".equals(action)) {
        Long id = Long.parseLong(request.getParameter("id"));
        userDao.delete(id);
    }

    // 목록 업데이트
    userList = userDao.findAll();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CRUD Example</title>
</head>
<body>
    <h1>User List</h1>
    <table border="1">
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
                <a href="index.jsp?action=delete&id=<%= user.getId() %>">Delete</a>
                <!-- 업데이트 페이지로 이동할 수 있도록 링크 추가 -->
                <a href="update.jsp?id=<%= user.getId() %>">Update</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>

    <h2>Add New User</h2>
    <form action="index.jsp" method="GET">
        <input type="hidden" name="action" value="add" />
        Name: <input type="text" name="name" required /><br/>
        Email: <input type="email" name="email" required /><br/>
        <input type="submit" value="Add User" />
    </form>
</body>
</html>
