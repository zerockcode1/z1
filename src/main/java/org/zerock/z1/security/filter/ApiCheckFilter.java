package org.zerock.z1.security.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter {

    private String pattern;
    private AntPathMatcher matcher;

    public ApiCheckFilter(String pattern){
        this.pattern = pattern;
        this.matcher = new AntPathMatcher();
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("api check filter.......................");
        String requestURI = request.getRequestURI();
        boolean matchResult = matcher.match(pattern,requestURI);

        if(matchResult == false){
            log.info("passing...............");
            filterChain.doFilter(request, response);
            return;
        }

        log.info("check target.................");

        String tokenValue = request.getHeader("Authorization");

        log.info(tokenValue);


        filterChain.doFilter(request, response);

    }
}
