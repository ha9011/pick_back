package com.toy.pick.sample;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class SampleEntity {

    @Id
    @GeneratedValue
    @Column(name = "sample_entity_id")
    private Long id;
    private String username;
}
