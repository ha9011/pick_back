package com.toy.pick.domain.sample;

import com.toy.pick.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sample extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "sample_id")
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private SampleStatus sampleStatus;

    @Builder
    public Sample(String name, SampleStatus sampleStatus) {
        this.name = name;
        this.sampleStatus = sampleStatus;
    }

}
