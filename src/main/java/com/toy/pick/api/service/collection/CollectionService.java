package com.toy.pick.api.service.collection;


import com.toy.pick.api.service.collection.response.MyCollectionsRes;
import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.collection.CollectionRepository;
import com.toy.pick.domain.member.Member;
import com.toy.pick.domain.member.MemberRepository;
import com.toy.pick.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CollectionService {
    private final CollectionRepository collectionRepository;
    private final MemberRepository memberRepository;


    public List<MyCollectionsRes> getMyCollections(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                ()-> {
                    throw new CustomException("존재하지않는 맴버입니다.");
                }
        );

        List<Collection> collections = collectionRepository.findAllByMember(member);
        return collections.stream().map(MyCollectionsRes::of)
                .collect(Collectors.toList());

    }
}
