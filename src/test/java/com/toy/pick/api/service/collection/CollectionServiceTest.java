package com.toy.pick.api.service.collection;

import com.toy.pick.api.service.collection.response.FollowCollectionRes;
import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.collection.CollectionRepository;
import com.toy.pick.domain.common.ItemStatus;
import com.toy.pick.domain.member.Member;
import com.toy.pick.domain.member.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@SpringBootTest
@ActiveProfiles("test")
@Rollback(value = false)
class CollectionServiceTest {



    @Autowired
    private CollectionService collectionService;
    @Autowired
    private CollectionRepository collectionRepository;
    @Autowired
    private MemberRepository memberRepository;
    @PersistenceContext
    private EntityManager em;

    @AfterEach
    void tearDown() {
//        memberCollectionRepository.deleteAllInBatch();
//        collectionRepository.deleteAllInBatch();
//        memberRepository.deleteAllInBatch();
    }

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


        //em.clear();
        // @Transaction 안에서  <- ( 설정시 )
        // 이미 영속성컨텍스트에 엔티티가 존재하면, 여러 값들을 격납하고 재조회해도, 기존에 있던걸로 다시 재이용할 뿐
        // DB에서 가져오지 않는다. 따라서. 연관관계 상호작용 메서드를 만들어서 관리하는게 유용하다.
        //https://velog.io/@ddangle/%EC%96%91%EB%B0%A9%ED%96%A5-%EC%97%B0%EA%B4%80%EA%B4%80%EA%B3%84%EC%9D%98-%ED%97%88%EC%A0%90



    }


    public Member createMember(String name){
        return Member.create(name, "naver", "nickName");
    }

    public Collection createCollection(String title, Member member){
        return Collection.builder()
                .title(title)
                .member(member)
                .memo("memo")
                .isDeletable(false)
                .status(ItemStatus.PUBLIC)
                .build();
    }



    @Test
    @DisplayName("나의 팔로우 제거")
    void removeMyFollowCollection() {

        // given
        Member memberA = createMember("memberA");
        Member memberB = createMember("memberB");
        memberRepository.saveAll(List.of(memberA, memberB));


        Collection cA1 = createCollection("c_A_1", memberA);
        Collection cB1 = createCollection("c_B_1", memberB);
        Collection cB2 = createCollection("c_B_2", memberB);
        Collection cB3 = createCollection("c_B_3", memberB);
        collectionRepository.saveAll(List.of(cA1, cB1, cB2, cB3));




    }
}