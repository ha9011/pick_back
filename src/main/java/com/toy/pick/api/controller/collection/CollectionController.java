package com.toy.pick.api.controller.collection;

import com.toy.pick.api.ApiResponseDto;
import com.toy.pick.api.controller.collection.request.PostMyCollectionsReq;
import com.toy.pick.api.controller.collection.request.PutMyCollectionsReq;
import com.toy.pick.api.service.collection.CollectionService;
import com.toy.pick.api.service.collection.response.FollowCollectionRes;
import com.toy.pick.api.service.collection.response.MyCollectionsInfoByCIdRes;
import com.toy.pick.api.service.collection.response.MyCollectionsRes;
import com.toy.pick.component.JwtTokenProvider;
import com.toy.pick.domain.collection.Collection;
import com.toy.pick.exception.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
            @ApiResponse(responseCode = "200", description = "SUCCESS", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", useReturnTypeSchema = true,
                    content = @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @GetMapping("/collection/my")
    public ApiResponseDto<List<MyCollectionsRes>> getMyCollections(
            @Parameter(example = "accesstoken", description ="상단에 Authorize로 등록하면, 아무값 넣어도 상관없음(swagger)" )
            @RequestHeader("Authorization") String accessToken
    ) throws Exception {
        try {
            Long id = jwtTokenProvider.getJwtPayloadId(accessToken);
            List<MyCollectionsRes> myCollections = collectionService.getMyCollections(id);
            return ApiResponseDto.ok(myCollections);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    };


    @Operation(summary = "내 컬렉션 생성", description = "내 컬렉션 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", useReturnTypeSchema = true,
                    content = @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @PostMapping("/collection/my")
    public ApiResponseDto<MyCollectionsRes> PostMyCollections(
            @Parameter(example = "accesstoken", description ="상단에 Authorize로 등록하면, 아무값 넣어도 상관없음(swagger)" )
            @RequestHeader("Authorization") String accessToken,
            @RequestBody @Valid PostMyCollectionsReq postMyCollectionsReq
    ) throws Exception {
        try {
            Long id = jwtTokenProvider.getJwtPayloadId(accessToken);
            MyCollectionsRes myCollection = collectionService.createCollection(postMyCollectionsReq, id);
            return ApiResponseDto.ok(myCollection);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    };

    @Operation(summary = "내 컬렉션 삭제", description = "내 컬렉션 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", useReturnTypeSchema = true,
                    content = @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @DeleteMapping("/collection/my")
    public ApiResponseDto<Object> deleteMyCollections(
            @Parameter(example = "accesstoken", description ="상단에 Authorize로 등록하면, 아무값 넣어도 상관없음(swagger)" )
            @RequestHeader("Authorization") String accessToken,
            @Parameter(example = "1", description ="삭제할 컬렉션 아이디, (기본 컬렉션은 삭제 X)" )
            @RequestParam("cId") Long cId
    ) throws Exception {
        try {
            Long id = jwtTokenProvider.getJwtPayloadId(accessToken);
            collectionService.removeMyCollection(cId, id);
            return ApiResponseDto.of(HttpStatus.OK, null, "컬렉션을 삭제하였습니다.","SUCCESS"  );
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    };

    @Operation(summary = "내 컬렉션 수정", description = "내 컬렉션 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", useReturnTypeSchema = true,
                    content = @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @PutMapping("/collection/my")
    public ApiResponseDto<Object> putMyCollections(
            @Parameter(example = "accesstoken", description ="상단에 Authorize로 등록하면, 아무값 넣어도 상관없음(swagger)" )
            @RequestHeader("Authorization") String accessToken,
            @Parameter(example = "1", description ="수정할 컬렉션 아이디, (기본 컬렉션은 삭제 X)" )
            @RequestParam("cId") Long cId,
            @RequestBody @Valid PutMyCollectionsReq putMyCollectionsReq
    ) throws Exception {
        try {
            Long id = jwtTokenProvider.getJwtPayloadId(accessToken);
            collectionService.updateCollection(id, cId, putMyCollectionsReq);
            return ApiResponseDto.of(HttpStatus.OK, null, "컬렉션을 수정하였습니다.","SUCCESS"  );
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    };

    @Operation(summary = "내 컬렉션 개별 조회", description = "내 컬렉션 개별 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", useReturnTypeSchema = true,
                    content = @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @GetMapping("/collection/my/info")
    public ApiResponseDto<MyCollectionsInfoByCIdRes> getMyCollectionsByCid(
            @Parameter(example = "accesstoken", description ="상단에 Authorize로 등록하면, 아무값 넣어도 상관없음(swagger)" )
            @RequestHeader("Authorization") String accessToken,
            @Parameter(example = "1", description ="조회할 컬렉션 아이디, (기본 컬렉션은 삭제 X)" )
            @RequestParam("cId") Long cId
    ) throws Exception {
        try {
            Long id = jwtTokenProvider.getJwtPayloadId(accessToken);
            MyCollectionsInfoByCIdRes collectionByCid = collectionService.getCollectionByCid(cId);
            return ApiResponseDto.ok(collectionByCid);
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
