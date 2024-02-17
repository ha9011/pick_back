package com.toy.pick.api.service.place;

import com.amazonaws.util.StringUtils;
import com.toy.pick.api.controller.place.request.PostCollectionPlaceReq;
import com.toy.pick.api.service.collectionPlace.CollectionPlaceService;
import com.toy.pick.api.service.member.response.GetUserInfoByIdRes;
import com.toy.pick.api.service.memberPlace.MemberPlaceService;
import com.toy.pick.api.service.s3.S3UploadService;
import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.collection.CollectionRepository;
import com.toy.pick.domain.place.Place;
import com.toy.pick.domain.place.PlaceRepository;
import com.toy.pick.domain.placeImage.PlaceImage;
import com.toy.pick.domain.placeImage.PlaceImageRepository;
import com.toy.pick.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
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
    private final CollectionRepository collectionRepository;
    private final CollectionPlaceService collectionPlaceService;
    private final MemberPlaceService memberPlaceService;

    @Transactional
    public void savePlaceWithImage(PostCollectionPlaceReq req, Long memberId) throws Exception {

        // * 컬렉션 체크 *
        Collection collection = collectionRepository.findById(req.getCollectionId()).orElseThrow(
                () -> new CustomException("해당 컬렉션은 존재하지 않습니다.")
        );

        // 0. url 접속 체크
        this.placeUrlConnCheck(req.getUrl());

        // 1. 장소 저장
        Place place = Place.create(req);
        Place newPlace = placeRepository.save(place);

        // 2. s3 저장 후 파일 경로 호출 -> 저장
        List<PlaceImage> placeImages = new ArrayList<>();
        for (MultipartFile file : req.getFiles()) {
            String path = s3UploadService.saveFile(file, newPlace.getId());
            PlaceImage placeImage = PlaceImage.create(path);
            PlaceImage savedPlaceImage = placeImageRepository.save(placeImage); // TODO saveAll 로 변경하기
            placeImages.add(savedPlaceImage);
        }

        // 장소에 해당하는 이미지 저장
        newPlace.updatePlaceImg(placeImages);

        // 3. 컬렉션과 장소 저장
        collectionPlaceService.savedCollectionPlace(newPlace, req.getCollectionId());

        // 4. 유저와 장소에 대한 메모, url 저장
        memberPlaceService.savedMemberPlace(memberId, newPlace, req);

        // 5. 해당 콜렉션 최근업데이트 새로 갱신
        collection.refreshLastUpdateAt(LocalDateTime.now());
    }

    private void placeUrlConnCheck(String url) throws Exception {
        log.info("url : " + url);
        // url이 존재 하면 실행
        if(!StringUtils.isNullOrEmpty(url)){

            RestTemplate restTemplate = new RestTemplate();

            try {
                // URL에 GET 요청 보내기
                ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
                // 응답코드 확인 (200번대는 성공을 의미)
                if (response.getStatusCode().is2xxSuccessful()) {
                    log.info("URL이 접속 가능합니다.");
                } else {
                    throw new CustomException("URL에 접속할 수 없습니다. 응답코드: " + response.getStatusCode());
                }
            } catch (Exception e) {
                throw new Exception("URL에 접속할 수 없습니다. 오류: " + e.getMessage());
            }

        }

    }

}
