package org.zerock.z1.security.filter;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.zerock.z1.security.dto.MemberAuthDTO;
import org.zerock.z1.security.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter {

    private String pattern;
    private AntPathMatcher matcher;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    public ApiCheckFilter(String pattern, JwtUtil jwtUtil, AuthenticationManager authenticationManager){
        this.pattern = pattern;
        this.matcher = new AntPathMatcher();
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
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

        log.info("check jwt target.................");

        String tokenValue = request.getHeader("Authorization");

        log.info(tokenValue);

        try {
            //Bearer
            String validateResult = jwtUtil.validateAndExtract(tokenValue.substring(7));
            log.info("validateResult:" + validateResult );

            if(SecurityContextHolder.getContext().getAuthentication() == null){
                Gson gson = new Gson();
                Map<String, Object> dataMap = gson.fromJson(validateResult, HashMap.class);

                log.info("dataMap: " + dataMap);

                List<String> roles = (List<String>) (dataMap.get("roles"));

                MemberAuthDTO memberAuthDTO = new MemberAuthDTO(
                        (String) dataMap.get("username"),
                        (String) dataMap.get("password"),
                        roles.stream().map(str -> new SimpleGrantedAuthority(str)).collect(Collectors.toList())
                );


                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        this.authenticationManager, null, memberAuthDTO.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

            //최종전송
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
