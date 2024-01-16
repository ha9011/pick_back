package com.toy.pick.config;

import com.toy.pick.component.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public FilterRegistrationBean<JwtTokenFilter> jwtFilter() {
        FilterRegistrationBean<JwtTokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtTokenFilter);

        // 적용할 URL 패턴 설정
        //registrationBean.setUrlPatterns(List.of("/v1/api/*"));

        // 제외할 URL 패턴 설정
        //registrationBean.addUrlPatterns("/v1/api/login");
        //        registrationBean.addUrlPatterns(
        //                "/v1/api/index.html",
        //                "/v1/api/h2-console/*",
        //                "/v1/api/swagger-ui/*",
        //                "/v1/api/v3/api-docs/*",
        //                "/v1/api/login/oauth2/code/*");
        return registrationBean;
    }
}
