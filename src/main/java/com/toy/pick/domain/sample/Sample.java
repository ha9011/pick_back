package com.toy.pick.domain.sample;

import com.toy.pick.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;


@Entity
@Getter
public class Sample extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "sample_id")
    private Long id;
    private String name;


}
