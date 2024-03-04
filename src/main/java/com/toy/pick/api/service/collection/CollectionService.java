package com.toy.pick.api.service.collection;


import com.toy.pick.api.controller.collection.request.PostMyCollectionsReq;
import com.toy.pick.api.controller.collection.request.PutMyCollectionsReq;
import com.toy.pick.api.service.collection.response.FollowCollectionRes;
import com.toy.pick.api.service.collection.response.MyCollectionsRes;
import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.collection.CollectionRepository;
import com.toy.pick.domain.collectionPlace.CollectionPlaceRepository;
import com.toy.pick.domain.common.ItemStatus;
import com.toy.pick.domain.member.Member;
import com.toy.pick.domain.member.MemberRepository;
import com.toy.pick.domain.place.Place;
import com.toy.pick.domain.place.PlaceRepository;
import com.toy.pick.exception.CustomException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CollectionService {
    private final CollectionRepository collectionRepository;
    private final CollectionPlaceRepository collectionPlaceRepository;
    private final MemberRepository memberRepository;
    private final PlaceRepository placeRepository;

    public List<MyCollectionsRes> getMyCollections(Long memberId) {
        Member member = findMemberById(memberId);
        List<Collection> collections = collectionRepository.findAllByMember(member);

        return collections.stream().map(MyCollectionsRes::of)
                .collect(Collectors.toList());

    }

    @Transactional
    public MyCollectionsRes createCollection(PostMyCollectionsReq req, Long memberId) {
        Member member = findMemberById(memberId);
        Collection collection = Collection.create(req, member);
        Collection newCollection = collectionRepository.save(collection);
        return MyCollectionsRes.of(newCollection);
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                ()-> new CustomException("존재하지않는 맴버입니다.")
        );
    }

    @Transactional
    public void removeMyCollection(Long cId, Long id) {
        Member member = this.findMemberById(id);

        Collection collection = collectionRepository.findById(cId).orElseThrow(
                () -> new CustomException("해당 컬렉션은 존재하지 않습니다.")
        );

        Member registeredMember = collection.getMember();
        if (!member.equals(registeredMember)) {
            throw new CustomException("해당 컬렉션을 등록한 유저가 아닙니다.");
        }

        boolean isDeletable = collection.isDeletable();

        if(isDeletable){
            collectionRepository.delete(collection);
        }else{
            throw new CustomException("기본 컬렉션은 삭제 할수 없습니다.");
        }

        // TODO 삭제 관련해서 같이 삭제할 친구들 고민해보기



    }

    @Transactional
    public void updateCollection(Long id, Long cId, PutMyCollectionsReq req) {
        Collection collection = collectionRepository.findById(cId).orElseThrow(
                () -> new CustomException("해당 컬렉션은 존재하지 않습니다.")
        );

        // 삭제할 Plcce Entity 가져오기
        List<Place> places = placeRepository.findAllById(req.getRemovePlaceIds());

        // 장소 삭제
        collectionPlaceRepository.removePlacesInCollectionByCid(collection, places);

        // 컬렉션 수정
        collection.updateCollectionInfo(req.getTitle(), req.getMemo());
    }
}
