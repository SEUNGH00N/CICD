package com.example.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필터 초기화 로직 (필요시)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        // 로그인 여부 확인
        boolean loggedIn = (session != null && session.getAttribute("loggedInUser") != null);
        String loginURI = httpRequest.getContextPath() + "/index.jsp";

        // 로그인 요청인지 확인
        boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);

        if (loggedIn || loginRequest) {
            // 로그인이 되어 있거나 로그인 페이지 요청이면 필터 체인 계속 진행
            chain.doFilter(request, response);
        } else {
            // 로그인되지 않은 경우, 경고 메시지와 함께 로그인 페이지로 리디렉션
            httpResponse.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = httpResponse.getWriter()) {
                out.println("<html><head><title>Login Required</title>");
                out.println("<script type='text/javascript'>");
                out.println("alert('로그인 해주세요!');");
                out.println("window.location.href='" + loginURI + "';");
                out.println("</script></head><body></body></html>");
            }
        }
    }

    @Override
    public void destroy() {
        // 필터 종료 로직 (필요시)
    }
}
