package com.tak.article.domain.home.interceptor;


import com.tak.article.domain.member.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();
        Enumeration<String> parameterNames = request.getParameterNames();

        log.info("whats url = {}", requestURI);

        if (session.getAttribute(SessionConst.LOGIN_MEMBER) == null && requestURI.equals("/logout")) {
            response.sendRedirect("/");
            return false;
        }

        if (session.getAttribute(SessionConst.LOGIN_MEMBER) == null && requestURI.equals("/signup") && parameterNames.hasMoreElements()) {
            response.sendRedirect("/signup");
            return false;
        }

        return true;
    }
}
