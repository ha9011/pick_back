package com.toy.pick.api.service.collection;


import com.toy.pick.api.controller.collection.request.PostMyCollectionsReq;
import com.toy.pick.api.service.collection.response.MyCollectionsRes;
import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.collection.CollectionRepository;
import com.toy.pick.domain.member.Member;
import com.toy.pick.domain.member.MemberRepository;
import com.toy.pick.exception.CustomException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CollectionService {
    private final CollectionRepository collectionRepository;
    private final MemberRepository memberRepository;


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
}
