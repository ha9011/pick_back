package com.toy.pick.api.controller.place;

import com.toy.pick.api.controller.place.request.PostCollectionPlaceReq;
import com.toy.pick.api.service.member.response.GetUserInfoByIdRes;
import com.toy.pick.api.service.place.PlaceService;
import com.toy.pick.component.JwtTokenProvider;
import com.toy.pick.exception.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.OneToMany;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(description = "컬렉션에 해당 장소 추가")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "FAIL", content = @Content(schema = @Schema(implementation = com.toy.pick.api.ApiResponse.class)))
    })
    @PostMapping("/place/collection")
    public com.toy.pick.api.ApiResponse<GetUserInfoByIdRes> postCollectionPlace(
            @Parameter(example = "accesstoken", description ="상단에 Authorize로 등록하면, 아무값 넣어도 상관없음(swagger)" )
            @RequestHeader("Authorization") String accessToken,
            @Parameter(description ="컬랙션-장소 BODY" )
            @ModelAttribute PostCollectionPlaceReq req
            ) throws Exception {
        try {

            System.out.println("-----postCollectionPlace------");
            System.out.println("req : " + req.toString());
            placeService.test(req);
            return null;
        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    };

}
