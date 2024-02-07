package com.toy.pick.api.service.collection;

import com.toy.pick.api.service.collection.response.FollowCollectionRes;
import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.collection.CollectionRepository;
import com.toy.pick.domain.common.ItemStatus;
import com.toy.pick.domain.member.Member;
import com.toy.pick.domain.member.MemberRepository;
import com.toy.pick.domain.memberCollection.MemberCollection;
import com.toy.pick.domain.memberCollection.MemberCollectionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Rollback(value = false)
@Transactional
class CollectionServiceTest {



    @Autowired
    private CollectionService collectionService;
    @Autowired
    private CollectionRepository collectionRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberCollectionRepository memberCollectionRepository;
    @PersistenceContext
    private EntityManager em;


    @Test
    @DisplayName("내가 다른 사람의 컬렉션을 팔로우 한다.")
    void followCollection() {
        // given
        Member memberA = createMember("memberA");
        Member memberB = createMember("memberB");
        Member memberC = createMember("memberC");
        memberRepository.saveAll(List.of(memberA, memberB, memberC));



        Collection cA1 = createCollection("c_A_1", memberA);
        Collection cA2 = createCollection("c_A_2", memberA);
        Collection cA3 = createCollection("c_A_3", memberA);
        Collection cB1 = createCollection("c_B_1", memberB);
        Collection cB2 = createCollection("c_B_2", memberB);
        Collection cB3 = createCollection("c_B_3", memberB);
        collectionRepository.saveAll(List.of(cA1, cA2, cA3, cB1, cB2, cB3));

        MemberCollection followB1 = createFollow(memberA, cB1);
        MemberCollection followB2 = createFollow(memberA, cB2);
        MemberCollection followB3 = createFollow(memberA, cB3);
        memberCollectionRepository.saveAll(List.of(followB1, followB2, followB3));

        em.clear();
        // 이미 영속성컨텍스트에 엔티티가 존재하면, 여러 값들을 격납하고 재조회해도, 기존에 있던걸로 다시 재이용할 뿐
        // DB에서 가져오지 않는다. 따라서. 연관관계 상호작용 메서드를 만들어서 관리하는게 유용하다.
        //https://velog.io/@ddangle/%EC%96%91%EB%B0%A9%ED%96%A5-%EC%97%B0%EA%B4%80%EA%B4%80%EA%B3%84%EC%9D%98-%ED%97%88%EC%A0%90

        // when
        List<FollowCollectionRes> followCollections = collectionService.getFollowCollections(memberA.getId());

        // then
        assertThat(followCollections).hasSize(3).extracting("id","title","memo","lastUpdateTime")
                .containsExactlyInAnyOrder(
                        tuple(4L,"c_B_1","memo","최근 업데이트(TODO)"),
                        tuple(5L,"c_B_2","memo","최근 업데이트(TODO)"),
                        tuple(6L,"c_B_3","memo","최근 업데이트(TODO)")
                );
    }


    public Member createMember(String name){
        return Member.create(name, "naver", "nickName");
    }

    public Collection createCollection(String title, Member member){
        return Collection.builder()
                .title(title)
                .member(member)
                .memo("memo")
                .isDeletableYn(false)
                .status(ItemStatus.PUBLIC)
                .build();
    }

    public MemberCollection  createFollow(Member member, Collection collection){
        return MemberCollection.create(member, collection);
    }
}