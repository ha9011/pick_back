package com.toy.pick.api.controller.login;

import com.toy.pick.api.ApiResponseDto;
import com.toy.pick.api.service.login.LoginService;
import com.toy.pick.api.service.login.dto.UserInfo;
import com.toy.pick.api.service.login.response.JwtTokenRes;
import com.toy.pick.exception.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Login", description = "Login API")
public class LoginController {

    private final LoginService loginService;



    @Operation(summary = "로그인", description = "소셜로그인을 통해 provider에게 받은 code를 백앤드에 전달")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", useReturnTypeSchema = true,
                    content = @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @GetMapping("/login/oauth2/code/{provider}")
    public ApiResponseDto<JwtTokenRes> LoginOauth(
            @Parameter(name = "provider", description = "sns로그인종류", required = true) @PathVariable String provider,
            @Parameter(name = "code", description = "OAuth2 로그인 후, provider가 제공한 코드", required = true) @RequestParam String code
    ) throws Exception {
        try {
            JwtTokenRes jwtTokenRes = loginService.loginSnsOauth(provider, code);
            return ApiResponseDto.ok(jwtTokenRes);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    };

    @Operation(description = "토큰의 payload 가져오기 - 테스트용")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST",
                    content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    })
    @GetMapping("/login/oauth2/tokenInfo")
    public ApiResponseDto<UserInfo> getTokenInfo(
            @Parameter(example = "accesstoken", description ="상단에 Authorize로 등록하면, 아무값 넣어도 상관없음(swagger)" )
            @RequestHeader("Authorization") String accessToken
            ) throws Exception {
        try {

            UserInfo userInfoApiRes = loginService.userTokenInfo(accessToken);
            return ApiResponseDto.ok(userInfoApiRes);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        } catch (Exception e) {
            throw new Exception();
        }
    };
}
