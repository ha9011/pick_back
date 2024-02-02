package com.toy.pick.api.service.place;

import com.toy.pick.api.controller.place.request.PostCollectionPlaceReq;
import com.toy.pick.api.service.member.response.GetUserInfoByIdRes;
import com.toy.pick.api.service.s3.S3UploadService;
import com.toy.pick.domain.place.Place;
import com.toy.pick.domain.place.PlaceRepository;
import com.toy.pick.domain.placeImage.PlaceImage;
import com.toy.pick.domain.placeImage.PlaceImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final S3UploadService s3UploadService;
    private final PlaceImageRepository placeImageRepository;

    @Transactional
    public Place savePlaceWithImage(PostCollectionPlaceReq req) throws IOException {

        Place place = Place.create(req);
        Place newPlace = placeRepository.save(place);
        List<PlaceImage> placeImages = new ArrayList<>();

        // 2. s3 저장 후 파일 경로 호출 -> 저장
        for (MultipartFile file : req.getFiles()) {
            String path = s3UploadService.saveFile(file, newPlace.getId());
            PlaceImage placeImage = PlaceImage.create(path);
            PlaceImage savedPlaceImage = placeImageRepository.save(placeImage);
            placeImages.add(savedPlaceImage);
        }

        newPlace.updatePlaceImg(placeImages);

        return newPlace;

    }
}
