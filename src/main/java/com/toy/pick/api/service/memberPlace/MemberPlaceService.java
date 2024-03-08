package com.toy.pick.api.service.memberPlace;

import com.toy.pick.api.controller.place.request.PostCollectionPlaceReq;
import com.toy.pick.domain.member.Member;
import com.toy.pick.domain.member.MemberRepository;
import com.toy.pick.domain.memberPlace.MemberPlace;
import com.toy.pick.domain.memberPlace.MemberPlaceRepository;
import com.toy.pick.domain.place.Place;
import com.toy.pick.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberPlaceService {

    private final MemberRepository memberRepository;
    private final MemberPlaceRepository memberPlaceRepository;

    public void savedMemberPlace(Long memberId, Place place){

        // TODO memberPlace member - place 둘다 (유니크 조건)
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException("존재하지 않은 유저입니다."));

        MemberPlace memberPlace = MemberPlace.create(member, place);

        MemberPlace save = memberPlaceRepository.save(memberPlace);

    }


    
}
