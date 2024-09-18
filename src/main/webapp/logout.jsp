<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // 세션 무효화
    session.invalidate();
    response.sendRedirect("index.jsp"); // 로그인 페이지로 리다이렉트
%>
