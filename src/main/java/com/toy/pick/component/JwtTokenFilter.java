package com.toy.pick.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.pick.api.ApiResponseDto;
import com.toy.pick.api.service.login.response.JwtTokenRes;
import com.toy.pick.api.service.member.MemberService;
import com.toy.pick.domain.member.Member;
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
    private final MemberService memberService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{


            // TODO 테스트용 -> 추후 삭제
            if (request.getRequestURI().equals("/v1/api/index.html")){
                filterChain.doFilter(request, response);
                return;
            }

            if (request.getRequestURI().startsWith("/v1/api/h2-console")){
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
                throw new Exception("토큰값이 유효하지 않습니다.");
            }

            final String token = header.replace("Bearer ", "");

            String newAccessToken = this.validateToken(token, response);
            if(newAccessToken != null){
                response.setStatus(HttpStatus.OK.value());
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                JwtTokenRes jwtTokenRes = JwtTokenRes.of(newAccessToken, null);
                String json = new ObjectMapper().writeValueAsString(ApiResponseDto.of(HttpStatus.OK, jwtTokenRes, "REFRESH" , "REFRESH"));
                response.getWriter().write(json);
                return;
            }

            //filterChain.doFilter(request, response);
        }catch (Exception e){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String json = new ObjectMapper().writeValueAsString(ApiResponseDto.of(HttpStatus.UNAUTHORIZED, e.getMessage(), "UNAUTHORIZED","UNAUTHORIZED"));
            response.getWriter().write(json);
            return ;
        }
        filterChain.doFilter(request, response);
    }

    private String validateToken(String accessToken, HttpServletResponse response) throws Exception {
        try{
            // 만료가 됐다면,-> 리프레시 토큰 -> 재발행 후 전달.
            boolean validateYn = jwtTokenProvider.validateToken(accessToken);
            if(!validateYn){


                // DB 에서 리프레시 토큰 값 가져오기

                Member member = memberService.findUserByAccessToken(accessToken);
                String refreshToken = member.getRefreshToken();
                //String accessToken1 = member.getAccessToken();
                // 유효성 검증 후 재발급
                String newAccessToken = jwtTokenProvider.validateRefreshAndCreateAccessToken(refreshToken);
                memberService.updateAccessToken(member.getId(), newAccessToken);

                return newAccessToken;

            }else{
                return null;
            }

        }catch(Exception e){
           throw new Exception(e.getMessage());
        }
    };

}