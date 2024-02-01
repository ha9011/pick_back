package com.toy.pick.api.service.place;

import com.toy.pick.api.ApiResponse;
import com.toy.pick.api.controller.place.request.PostCollectionPlaceReq;
import com.toy.pick.api.service.member.response.GetUserInfoByIdRes;
import com.toy.pick.api.service.s3.S3UploadService;
import com.toy.pick.domain.place.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final S3UploadService s3UploadService;

    public ApiResponse<GetUserInfoByIdRes> test(PostCollectionPlaceReq req) throws IOException {
        String s = s3UploadService.saveFile(req.getFiles().get(0));
        System.out.println("s : " + s);
        return null;
    }
}
