package com.toy.pick.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.pick.api.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.secret.key}")
    private String scretkey;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("=======");
        System.out.println(request.getRequestURI());

        // TODO 테스트용 -> 추후 삭제
        if (request.getRequestURI().equals("/v1/api/index.html")){
            log.info("---filter검증--- 시작1");
            filterChain.doFilter(request, response);
            return;
        }

        // swagger
        if (request.getRequestURI().startsWith("/v1/api/swagger-ui/")
            || request.getRequestURI().startsWith("/v1/api/v3/api-docs")){
            filterChain.doFilter(request, response);
            return;
        }

        // 최초 로그인
        if (request.getRequestURI().startsWith("/v1/api/login/oauth2/code/")){
            filterChain.doFilter(request, response);
            return;
        }


        final String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Missing or invalid Authorization header");
            return;
        }

        final String token = header.substring(7);

        this.validateToken(token, response);

        filterChain.doFilter(request, response);
    }

    private void validateToken(String token, HttpServletResponse response) throws IOException {
        boolean validateYn = jwtTokenProvider.validateToken(token);
        // 유효하지 않다면..
        if(!validateYn){
            System.out.println("....");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String json = new ObjectMapper().writeValueAsString(ApiResponse.of(HttpStatus.UNAUTHORIZED, "유효하지 않는 토큰값입니다", null));

            response.getWriter().write(json);
        }
    }

}