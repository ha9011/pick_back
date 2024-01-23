package com.toy.pick.api.controller.collection;

import com.toy.pick.api.ApiResponse;
import com.toy.pick.api.service.collection.CollectionService;
import com.toy.pick.api.service.collection.response.MyCollectionsRes;
import com.toy.pick.api.service.login.response.JwtTokenRes;
import com.toy.pick.component.JwtTokenProvider;
import com.toy.pick.domain.collection.Collection;
import com.toy.pick.exception.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Collection", description = "Collection API")
public class CollectionController {

    private final JwtTokenProvider jwtTokenProvider;
    private final CollectionService collectionService;

    @Operation(summary = "내 컬렉션 조회", description = "내 컬렉션 조회")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "BAD_REQUEST", useReturnTypeSchema = true,
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @GetMapping("/collection/my")
    public ApiResponse<List<MyCollectionsRes>> getMyCollections(
            @Parameter(example = "accesstoken", description ="상단에 Authorize로 등록하면, 아무값 넣어도 상관없음(swagger)" )
            @RequestHeader("Authorization") String accessToken
    ) throws Exception {
        try {
            Long id = jwtTokenProvider.getJwtPayloadId(accessToken);
            List<MyCollectionsRes> myCollections = collectionService.getMyCollections(id);
            System.out.println(myCollections.toString());
            return ApiResponse.ok(myCollections);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    };

    @Operation(summary = "내 컬렉션 생성", description = "내 컬렉션 생성")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "BAD_REQUEST", useReturnTypeSchema = true,
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @PostMapping("/collection/my")
    public ApiResponse<List<MyCollectionsRes>> PostMyCollections(
            @Parameter(example = "accesstoken", description ="상단에 Authorize로 등록하면, 아무값 넣어도 상관없음(swagger)" )
            @RequestHeader("Authorization") String accessToken
    ) throws Exception {
        try {
            Long id = jwtTokenProvider.getJwtPayloadId(accessToken);
            List<MyCollectionsRes> myCollections = collectionService.getMyCollections(id);
            System.out.println(myCollections.toString());
            return ApiResponse.ok(myCollections);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    };

//    @Operation(summary = "로그인", description = "소셜로그인을 통해 provider에게 받은 code를 백앤드에 전달")
//    @ApiResponses(value = {
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "BAD_REQUEST", useReturnTypeSchema = true,
//                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
//    })
//    @GetMapping("/collection/my")
//    public ApiResponse<JwtTokenRes> LoginOauth(
//            @Parameter(name = "provider", description = "sns로그인종류", required = true) @PathVariable String provider,
//            @Parameter(name = "code", description = "OAuth2 로그인 후, provider가 제공한 코드", required = true) @RequestParam String code
//    ) throws Exception {
//        try {
//            JwtTokenRes jwtTokenRes = loginService.loginSnsOauth(provider, code);
//            return ApiResponse.ok(jwtTokenRes);
//        } catch (CustomException e) {
//            throw new CustomException(e.getMessage());
//        } catch (Exception e) {
//            throw new Exception();
//        }
//    };

}
