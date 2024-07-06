package com.tak.article.domain.home.interceptor;

import com.tak.article.domain.member.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginUserCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();

        if (session.getAttribute(SessionConst.LOGIN_MEMBER) != null) {
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
