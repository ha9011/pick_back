package com.toy.pick.api.controller.login;

import com.toy.pick.api.ApiResponse;
import com.toy.pick.api.service.login.LoginService;
import com.toy.pick.api.service.login.response.JwtTokenRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login/oauth2/code/{provider}")
    public ApiResponse<JwtTokenRes> LoginOauth(@PathVariable String provider,
                             @RequestParam String code) throws Exception {
        try {
            return loginService.loginSnsOauth(provider, code);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e);
            throw new Exception();
        }
    };

    @GetMapping("/login/oauth2/tokenInfo")
    public ApiResponse<Object> getTokenInfo(
            @RequestHeader("Authorization") String accessToken
            ) throws Exception {
        try {
            return loginService.userTokenInfo(accessToken);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e);
            throw new Exception();
        }
    };

}
