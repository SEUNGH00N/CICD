package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginForm(HttpServletRequest request) {
        // 에러 메시지가 있을 경우 전달
        String errorMessage = (String) request.getAttribute("errorMessage");
        request.setAttribute("errorMessage", errorMessage);
        return "login"; // 로그인 페이지를 반환
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest request) {
        // 간단한 로그인 로직 (실제 인증 로직을 추가해야 함)
        if ("user".equals(username) && "password".equals(password)) {
            // 세션에 로그인 정보 저장
            request.getSession().setAttribute("LOGGED_IN_USER", username);
            return "redirect:/users"; // 로그인 성공 후 리다이렉트
        }

        // 로그인 실패 시 에러 메시지와 함께 로그인 페이지로 리다이렉트
        request.setAttribute("errorMessage", "아이디 또는 비밀번호가 잘못되었습니다.");
        return "forward:/login";
    }
}
