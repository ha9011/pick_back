package com.toy.pick.api.controller.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.toy.pick.api.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login/oauth2/code/{provider}")
    public Object LoginOauth(@PathVariable String provider,
                             @RequestParam String code) throws Exception {
        try {
            loginService.loginSnsOauth(provider, code);
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e);
            throw new Exception();
        }
    };

}
