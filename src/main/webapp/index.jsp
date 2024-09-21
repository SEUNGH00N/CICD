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
                response.sendRedirect("index.jsp"); // 로그인 후 다시 같은 페이지로 리다이렉트
                return; // 이후 코드 실행 중단
            } else {
                request.setAttribute("errorMessage", "Invalid email or password");
            }
        }
    }

    // 로그인된 사용자 가져오기
    User loggedInUser = (User) session.getAttribute("loggedInUser");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <aside class="sidebar">
            <h2 class="sidebar__title">Navigation</h2>
            <nav>
                <a class="sidebar__link" href="index.jsp">Home</a>
                <a class="sidebar__link" href="userList.jsp">User List</a>
                <a class="sidebar__link" href="addUser.jsp">Add New User</a>
                <% if (loggedInUser != null) { %>
                    <a class="sidebar__link" href="logout.jsp">Logout</a>
                <% } %>
            </nav>
        </aside>
        
        <main class="content">
            <% if (loggedInUser != null) { %>
                <h1 class="content__welcome-title">Welcome, <%= loggedInUser.getName() %>!</h1>
                <section class="board">
                    <h2 class="board__title">Board</h2>
                    <ul class="board__list">
                        <li class="board__item"><strong>Post 1:</strong> This is a dummy post.</li>
                        <li class="board__item"><strong>Post 2:</strong> Another dummy post here.</li>
                        <li class="board__item"><strong>Post 3:</strong> Yet another post for testing.</li>
                    </ul>
                </section>
            <% } else { %>
                <h1 class="content__login-title">Login</h1>
                <% if (request.getAttribute("errorMessage") != null) { %>
                    <p class="content__error-message"><%= request.getAttribute("errorMessage") %></p>
                <% } %>
                <form class="content__login-form" action="index.jsp?action=login" method="POST">
                    <label for="email">Email:</label>
                    <input type="email" id="email" class="input--email" name="email" required />
                    <label for="password">Password:</label>
                    <input type="password" id="password" class="input--password" name="password" required />
                    <button class="button button--submit" type="submit">Login</button>
                </form>
            <% } %>
        </main>
    </div>
</body>
</html>