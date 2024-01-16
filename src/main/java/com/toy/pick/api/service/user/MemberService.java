package com.toy.pick.api.service.user;

import com.toy.pick.domain.user.Member;
import com.toy.pick.domain.user.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
