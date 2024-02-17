package com.toy.pick.api.service.collection;


import com.toy.pick.api.controller.collection.request.PostMyCollectionsReq;
import com.toy.pick.api.service.collection.response.FollowCollectionRes;
import com.toy.pick.api.service.collection.response.MyCollectionsRes;
import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.collection.CollectionRepository;
import com.toy.pick.domain.common.ItemStatus;
import com.toy.pick.domain.member.Member;
import com.toy.pick.domain.member.MemberRepository;
import com.toy.pick.domain.memberCollection.MemberCollection;
import com.toy.pick.domain.memberCollection.MemberCollectionRepository;
import com.toy.pick.exception.CustomException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CollectionService {
    private final CollectionRepository collectionRepository;
    private final MemberRepository memberRepository;
    private final MemberCollectionRepository memberCollectionRepository;


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
    }


    public List<FollowCollectionRes> getFollowCollections(Long id) {
        Member memberWithFollowCollection = memberRepository.findByMemberWithFollowCollection(id);

        List<MemberCollection> memberCollections = memberWithFollowCollection.getMemberCollections(); // n+1 의심
        List<Collection> collects = memberCollections.stream().map(MemberCollection::getCollection).collect(Collectors.toList());
        return collects.stream()
                .filter(c-> c.getStatus().equals(ItemStatus.PUBLIC))
                .map(FollowCollectionRes::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeMyFollowCollection(Long cId, Long memberId) {
        MemberCollection memberCollection = memberCollectionRepository.findByMemberIdAndCollectionId(memberId, cId).orElseThrow(
                () -> new CustomException("해당 팔로우는 존재하지 않습니다.")
        );
        System.out.println(memberCollection.getId());
        memberCollectionRepository.delete(memberCollection);
    }
}
