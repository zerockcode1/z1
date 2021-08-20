package org.zerock.z1.security.filter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.zerock.z1.security.dto.MemberAuthDTO;
import org.zerock.z1.security.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Setter
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {

    private JwtUtil jwtUtil;

    public ApiLoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        log.info("-------------------------------------------------------------");
        log.info("attemptAuthentication");
        log.info("-------------------------------------------------------------");

        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        log.info("id: " + id +" pw: " + pw);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(id,pw);


        Authentication authResult =  this.getAuthenticationManager().authenticate(authenticationToken);

        log.info(authResult);

        return authResult;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        log.info("success login after.................");

        MemberAuthDTO principal = (MemberAuthDTO) authResult.getPrincipal();
        log.info(principal);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, String> responseMap = new HashMap<>();

        //make MemberAuthDTO -> Map
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("username",principal.getUsername());
        dataMap.put("password", principal.getPassword());
        dataMap.put("roles", principal.getAuthorities().stream().map(auth -> auth.getAuthority()).collect(Collectors.toList()));


        try {
            String jwtStr = jwtUtil.generateToken(new GsonBuilder().create().toJson(dataMap));
            responseMap.put("token", jwtStr);
        } catch (Exception e) {
            e.printStackTrace();
        }


        response.getWriter().println(new GsonBuilder().create().toJson(responseMap));


    }
}
