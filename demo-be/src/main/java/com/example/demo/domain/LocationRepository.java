package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

    List<LocationEntity> findAllById(Long locationId);

    <S extends LocationEntity> S save(S entity);

    Optional<LocationEntity> findByLocationCode(String locationCode);
}

