package com.toy.pick.api.controller.collectionPlace;

import com.toy.pick.api.ApiResponseDto;
import com.toy.pick.api.controller.collection.request.PostMyCollectionsReq;
import com.toy.pick.api.controller.collection.request.PutMyCollectionsReq;
import com.toy.pick.api.controller.collectionPlace.request.RemovePlaceInMyCollectionsByCIdReq;
import com.toy.pick.api.service.collection.CollectionService;
import com.toy.pick.api.service.collection.response.MyCollectionsInfoByCIdRes;
import com.toy.pick.api.service.collection.response.MyCollectionsRes;
import com.toy.pick.api.service.collectionPlace.CollectionPlaceService;
import com.toy.pick.component.JwtTokenProvider;
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
public class CollectionPlaceController {

    private final JwtTokenProvider jwtTokenProvider;
    private final CollectionPlaceService collectionPlaceService;


    @Operation(summary = "특정 컬렉션에 장소 삭제", description = "특정 컬렉션에 장소 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", useReturnTypeSchema = true,
                    content = @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @DeleteMapping("/collection/place")
    public ApiResponseDto<Object> removePlaceInMyCollectionsByCId(
            @Parameter(example = "accesstoken", description ="상단에 Authorize로 등록하면, 아무값 넣어도 상관없음(swagger)" )
            @RequestHeader("Authorization") String accessToken,
            @Parameter(example = "1", description ="컬렉션 아이디, (기본 컬렉션은 삭제 X)" )
            @RequestParam("cId") Long cId,
            @RequestBody @Valid RemovePlaceInMyCollectionsByCIdReq req
    ) throws Exception {
        try {
            Long id = jwtTokenProvider.getJwtPayloadId(accessToken);
            collectionPlaceService.removePlaceInCollection(cId, req);
            return ApiResponseDto.of(HttpStatus.OK, null, "컬렉션에 장소들을 삭제하였습니다.","SUCCESS"  );
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    };

}
