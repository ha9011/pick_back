package com.toy.pick.domain.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Long> {

    List<Sample> findByName(String name);

}
