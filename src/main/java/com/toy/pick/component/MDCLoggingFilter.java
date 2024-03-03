package com.toy.pick.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.pick.api.ApiResponseDto;
import com.toy.pick.api.service.login.response.JwtTokenRes;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // filter 최고 우선순위
public class MDCLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request; //
//        final String header = req.getHeader("Authorization");
//        final String token = header.replace("Bearer ", "");



        MDC.put("uuid", UUID.randomUUID().toString().replaceAll("-", ""));
        chain.doFilter(request, response);
        MDC.clear();
    }

}