package com.toy.pick.api.service.member;

import com.toy.pick.api.service.member.response.GetUserInfoByIdRes;
import com.toy.pick.domain.member.Member;
import com.toy.pick.domain.member.MemberRepository;
import com.toy.pick.exception.CustomException;
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
    public void updateAccessToken(long id, String newAccessToken) {
        memberRepository.findById(id).ifPresentOrElse(
                m->m.updateAccessToken(newAccessToken),
                () -> {
                    throw new CustomException("존재하지 않은 유저입니다.");
                }
        );
    }

    public GetUserInfoByIdRes getUserInfoById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new CustomException("존재하지 않은 유저입니다."));
        return GetUserInfoByIdRes.of(member);
    }

}
