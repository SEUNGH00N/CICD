package com.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SsoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 세션에서 로그인 정보 확인
        Object loggedInUser = request.getSession().getAttribute("LOGGED_IN_USER");

        if (loggedInUser == null) {
            // 로그인 정보가 없으면 에러 메시지와 함께 로그인 페이지로 리다이렉트
            request.setAttribute("errorMessage", "로그인이 필요합니다.");
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        }

        // 로그인 정보가 있으면 요청 진행
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        // 요청 후 처리 로직 (필요하지 않다면 비워둡니다)
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 요청 완료 후 처리 로직 (필요하지 않다면 비워둡니다)
    }
}
