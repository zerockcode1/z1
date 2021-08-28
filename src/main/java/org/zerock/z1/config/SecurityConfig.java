package org.zerock.z1.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.zerock.z1.security.filter.ApiCheckFilter;
import org.zerock.z1.security.filter.ApiLoginFilter;
import org.zerock.z1.security.handler.CustomAuthenticationEntryPoint;
import org.zerock.z1.security.util.JwtUtil;

@Configuration
@Log4j2
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        log.info("------------------configure-------------");

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

/*
        http.authorizeRequests()
                .antMatchers("/api/signup").permitAll()
                .antMatchers("/demo/*").permitAll()
                .anyRequest().authenticated()

                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
*/

        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
        http.csrf().disable();

        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ApiCheckFilter apiCheckFilter()throws Exception{

        ApiCheckFilter checkFilter = new ApiCheckFilter("/api/movies/**/*", jwtUtil(),authenticationManager());

        return checkFilter;
    }

    @Bean
    public ApiLoginFilter apiLoginFilter()throws Exception {

        ApiLoginFilter apiLoginFilter =  new ApiLoginFilter("/api/login");

        apiLoginFilter.setAuthenticationManager(authenticationManager());

        apiLoginFilter.setJwtUtil(jwtUtil());

        return apiLoginFilter;
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new CustomAuthenticationEntryPoint();
    }
}
