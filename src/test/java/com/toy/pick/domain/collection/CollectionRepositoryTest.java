package com.toy.pick.domain.collection;

import com.toy.pick.api.controller.place.request.PostCollectionPlaceReq;
import com.toy.pick.api.service.collection.CollectionService;
import com.toy.pick.config.JpaAuditingConfig;
import com.toy.pick.domain.collectionPlace.CollectionPlace;
import com.toy.pick.domain.collectionPlace.CollectionPlaceRepository;
import com.toy.pick.domain.common.ItemStatus;
import com.toy.pick.domain.member.Member;
import com.toy.pick.domain.member.MemberRepository;
import com.toy.pick.domain.memberCollection.MemberCollection;
import com.toy.pick.domain.memberCollection.MemberCollectionRepository;
import com.toy.pick.domain.place.Place;
import com.toy.pick.domain.place.PlaceRepository;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
//@SpringBootTest
@EntityListeners(AuditingEntityListener.class)
@ActiveProfiles("test")
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaAuditingConfig.class)
class CollectionRepositoryTest {

    @Autowired
    private CollectionRepository collectionRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private CollectionPlaceRepository collectionPlaceRepository;
//
    @Autowired
    EntityManager em;


    @Test
    @DisplayName("내가 등록한 컬렉션 조회")
    void findMyCollection() {
        // given
        // given
        Member memberA = createMember("memberA");
        memberRepository.saveAll(List.of(memberA));
        long id = memberA.getId();
        Collection cA1 = createCollection("c_A_1", memberA);
        Collection cA2 = createCollection("c_A_2", memberA);
        Collection cA3 = createCollection("c_A_3", memberA);
        collectionRepository.saveAll(List.of(cA1, cA2, cA3));

        // ca1
        PostCollectionPlaceReq req1 = new PostCollectionPlaceReq(cA1.getId(), "장소1", "서울", "서울 디테일", "1.1", "2.2", "CAFE", "PUBLIC", null, null, null);
        PostCollectionPlaceReq req2 = new PostCollectionPlaceReq(cA1.getId(), "장소2", "부천", "부천 디테일", "1.1", "2.2", "CAFE", "PUBLIC", null, null, null);
        PostCollectionPlaceReq req3 = new PostCollectionPlaceReq(cA1.getId(), "장소3", "배곧", "배곧 디테일", "1.1", "2.2", "CAFE", "PUBLIC", null, null, null);
        Place place1 = createPlace(req1);
        Place place2 = createPlace(req2);
        Place place3 = createPlace(req3);
        placeRepository.saveAll(List.of(place1, place2, place3));

        CollectionPlace cp1 = createCollectionPlace(cA1, place1);
        CollectionPlace cp2 = createCollectionPlace(cA1, place2);
        CollectionPlace cp3 = createCollectionPlace(cA1, place3);
        collectionPlaceRepository.saveAll(List.of(cp1, cp2, cp3));

        //ca2
        PostCollectionPlaceReq reqB1 = new PostCollectionPlaceReq(cA1.getId(), "장소b_1", "서울", "서울 디테일", "1.1", "2.2", "CAFE", "PUBLIC", null, null, null);
        PostCollectionPlaceReq reqB2 = new PostCollectionPlaceReq(cA1.getId(), "장소b_2", "부천", "부천 디테일", "1.1", "2.2", "CAFE", "PUBLIC", null, null, null);
        PostCollectionPlaceReq reqB3 = new PostCollectionPlaceReq(cA1.getId(), "장소b_3", "배곧", "배곧 디테일", "1.1", "2.2", "CAFE", "PUBLIC", null, null, null);
        Place placeB1 = createPlace(reqB1);
        Place placeB2 = createPlace(reqB2);
        Place placeB3 = createPlace(reqB3);
        placeRepository.saveAll(List.of(placeB1, placeB2, placeB3));


        CollectionPlace cpB1 = createCollectionPlace(cA2, placeB1);
        CollectionPlace cpB2 = createCollectionPlace(cA2, placeB2);
        CollectionPlace cpB3 = createCollectionPlace(cA2, placeB3);
        collectionPlaceRepository.saveAll(List.of(cpB1, cpB2, cpB3));
        // when

        em.flush();
        em.clear();

        System.out.println("---- id :" + id);
        Member member = memberRepository.findById(id).get();
        List<Collection> allByMember = collectionRepository.findAllByMember(member);

        System.out.println(allByMember.size());
        System.out.println(allByMember.get(0).getCollectionPlaces().get(0).getId());
        System.out.println(allByMember.get(0).getCollectionPlaces().get(0).getUpdatedAt());
//
//        em.flush();
//        em.clear();

        // then
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

    public Place createPlace(PostCollectionPlaceReq req){
        return Place.create(req);
    }

    public CollectionPlace createCollectionPlace(Collection collection, Place place){
        return CollectionPlace.create(collection, place);
    }


}