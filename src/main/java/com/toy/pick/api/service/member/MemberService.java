package com.toy.pick.api.service.member;

import com.toy.pick.domain.member.Member;
import com.toy.pick.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findUserByAccessToken(String accessToken){
        return memberRepository.findByAccessToken(accessToken);
    }

    @Transactional
    public void updateAccessToken(long memberId, String newAccessToken) {
        memberRepository.findById(memberId).ifPresent(member -> member.updateAccessToken(newAccessToken));
    }
}
