package com.toy.pick.domain.sample;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@ActiveProfiles("test")
class SampleTest {

    @Autowired
    EntityManager em;


    @Test
    @DisplayName("QueryDsl 샘플 테스트")
    void QueryDsl_Sample_Test() {
        // given
        Sample sample = Sample.builder()
                .sampleStatus(SampleStatus.SAMPLE)
                .name("blue")
                .build();
        em.persist(sample);

        JPAQueryFactory query = new JPAQueryFactory(em);
        QSample qSample = new QSample("s");

        Sample result = query
                .selectFrom(qSample)
                .fetchOne();

        // when
        System.out.println("result : "+ result);
        // then
        assertThat(result).isEqualTo(sample);
    }
}