package org.zerock.z1.security.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Log4j2
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {

        log.error(authException.getMessage());
        log.error("----------------------------------------");

        Arrays.stream(authException.getStackTrace()).forEach(stackTraceElement -> log.error(stackTraceElement));

        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);
        res.getWriter().write("{\"msg\":\"403\"}");
    }
}