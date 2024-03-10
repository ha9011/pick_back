package com.toy.pick.api.controller.place;

import com.toy.pick.api.ApiResponseDto;
import com.toy.pick.api.controller.place.request.PostCollectionPlaceReq;
import com.toy.pick.api.controller.place.request.PostPlaceInCollectionReq;
import com.toy.pick.api.controller.place.request.PostPlaceReq;
import com.toy.pick.api.service.collectionPlace.CollectionPlaceService;
import com.toy.pick.api.service.memberPlace.MemberPlaceService;
import com.toy.pick.api.service.place.PlaceService;
import com.toy.pick.api.service.place.response.SavePlaceWithImageRes;
import com.toy.pick.component.JwtTokenProvider;
import com.toy.pick.domain.collectionPlace.CollectionPlace;
import com.toy.pick.domain.place.Place;
import com.toy.pick.exception.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(description = "컬렉션에 해당 장소 추가")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "FAIL", content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    })
    @PostMapping(value = "/place/withCollection", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponseDto<Object> postCollectionPlace(
            @Parameter(example = "accesstoken", description ="상단에 Authorize로 등록하면, 아무값 넣어도 상관없음(swagger)" )
            @RequestHeader("Authorization") String accessToken,
            @Parameter(description ="multipart/form-data 형태로 장소 이미지가 포함됩니다." )
            @ModelAttribute PostCollectionPlaceReq req
            ) throws Exception {
        try {

            // TODO 기획 2-10 어떻게 할지 확인 후 추가
            Long memberId = jwtTokenProvider.getJwtPayloadId(accessToken);

            placeService.savePlaceWithImage(req, memberId);

            return ApiResponseDto.of(HttpStatus.OK, null, "컬렉션에 장소가 추가되었습니다.","SUCCESS"  );
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    };

    @Operation(description = "장소만 추가")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "FAIL", content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    })
    @PostMapping(value =  "/place", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponseDto<SavePlaceWithImageRes> postPlace(
            @Parameter(example = "accesstoken", description ="상단에 Authorize로 등록하면, 아무값 넣어도 상관없음(swagger)" )
            @RequestHeader("Authorization") String accessToken,
            @Parameter(description ="multipart/form-data 형태로 장소 이미지가 포함됩니다." )
            @ModelAttribute PostPlaceReq req
    ) throws Exception {
        try {
            // TODO 기획 2-10 어떻게 할지 확인 후 추가
            Long memberId = jwtTokenProvider.getJwtPayloadId(accessToken);
            SavePlaceWithImageRes res = placeService.savePlaceWithImage(req, memberId);
            return ApiResponseDto.ok(res);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    };

    @Operation(description = "추가한 장소를 컬렉션에 넣기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "FAIL", content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    })
    @PostMapping("/place/inCollection")
    public ApiResponseDto<Object> postCollectionPlace(
            @Parameter(example = "accesstoken", description ="상단에 Authorize로 등록하면, 아무값 넣어도 상관없음(swagger)" )
            @RequestHeader("Authorization") String accessToken,
            @Parameter(description ="컬랙션-장소 BODY" )
            @RequestBody @Valid PostPlaceInCollectionReq req
    ) throws Exception {
        try {
            // TODO 기획 2-10 어떻게 할지 확인 후 추가
            Long memberId = jwtTokenProvider.getJwtPayloadId(accessToken);
            placeService.savePlaceInCollection(req, memberId);
            return ApiResponseDto.of(HttpStatus.OK, null, "컬렉션에 장소가 추가되었습니다.","SUCCESS"  );
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    };


//
//    @Operation(description = "컬렉션에 해당하는 장소 정보 조회")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "SUCCESS", useReturnTypeSchema = true),
//            @ApiResponse(responseCode = "400", description = "FAIL", content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
//    })
//    @GetMapping("/place/collection")
//    public ApiResponseDto<Object> postCollectionPlace(
//            @Parameter(example = "accesstoken", description ="상단에 Authorize로 등록하면, 아무값 넣어도 상관없음(swagger)" )
//            @RequestHeader("Authorization") String accessToken,
//            @Parameter(description ="컬랙션-장소 BODY" )
//            @ModelAttribute PostCollectionPlaceReq req
//    ) throws Exception {
//        try {
//
//            // TODO 기획 2-10 어떻게 할지 확인 후 추가
//
//            Place place = placeService.savePlaceWithImage(req);
//            CollectionPlace collectionPlace = collectionPlaceService.savedCollectionPlace(place, req.getCollectionId());
//            return ApiResponseDto.of(HttpStatus.OK, null, "컬렉션에 장소가 추가되었습니다.","SUCCESS"  );
//        } catch (CustomException e) {
//            throw new CustomException(e.getMessage());
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    };

}
