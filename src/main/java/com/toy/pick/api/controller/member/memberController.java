package com.toy.pick.api.controller.member;

import com.toy.pick.api.ApiResponseDto;
import com.toy.pick.api.service.login.dto.UserInfo;
import com.toy.pick.api.service.member.MemberService;
import com.toy.pick.api.service.member.response.GetUserInfoByIdRes;
import com.toy.pick.component.JwtTokenProvider;
import com.toy.pick.domain.member.Member;
import com.toy.pick.exception.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class memberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(description = "로그인 유저 정보 가져오기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "FAIL",
                    content =  @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    })
    @GetMapping("/member/me")
    public ApiResponseDto<GetUserInfoByIdRes> getMemberInfo(
            @Parameter(example = "accesstoken", description ="상단에 Authorize로 등록하면, 아무값 넣어도 상관없음(swagger)" )
            @RequestHeader("Authorization") String accessToken
    ) throws Exception {
        try {
            Long id = jwtTokenProvider.getJwtPayloadId(accessToken);
            GetUserInfoByIdRes userInfo = memberService.getUserInfoById(id);
            return ApiResponseDto.ok(userInfo);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        } catch (Exception e) {
            throw new Exception();
        }
    };

    
}
