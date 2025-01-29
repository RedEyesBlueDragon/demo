package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportationRepository extends JpaRepository<TransportationEntity, Long> {

    List<TransportationEntity> findAllById(Long transportationId);

    <S extends TransportationEntity> S save(S entity);

    List<TransportationEntity> findAllByOriginId(Long originId);

    List<TransportationEntity> findAllByDestinationId(Long destinationId);

    List<TransportationEntity> findByOriginAndDestinationAndTransportationType(LocationEntity origin, LocationEntity destination, TransportationType transportationType);
}
