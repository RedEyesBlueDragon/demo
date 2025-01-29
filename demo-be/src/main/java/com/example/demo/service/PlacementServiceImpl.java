package com.example.demo.service;

import com.example.demo.Exception.DomainException;
import com.example.demo.data.LocationDto;
import com.example.demo.data.Request.AddLocationRequest;
import com.example.demo.data.Request.AddTransportationRequest;
import com.example.demo.data.Response.GetAllLocationsResponse;
import com.example.demo.data.Response.GetAllTransportationsResponse;
import com.example.demo.data.Response.GetLocationResponse;
import com.example.demo.data.Response.GetTransportationResponse;
import com.example.demo.data.TransportationDto;
import com.example.demo.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlacementServiceImpl implements PlacementService {

    private final TransportationRepository transportationRepository;

    private final LocationRepository locationRepository;

    public PlacementServiceImpl(TransportationRepository transportationRepository,
                                LocationRepository locationRepository) {
        this.transportationRepository = transportationRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public void saveOrUpdateLocation(LocationDto location) {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCity(location.getCity().toUpperCase());
        locationEntity.setCountry(location.getCountry().toUpperCase());
        locationEntity.setLocationCode(location.getLocationCode().toUpperCase());
        locationEntity.setName(location.getName().toUpperCase());
        Optional.ofNullable(location.getId())
                .ifPresentOrElse(s ->
                        {
                            locationEntity.setId(s);
                            locationRepository.save(locationEntity);
                        },
                        () -> locationRepository.save(locationEntity));
    }

    @Override
    public GetAllLocationsResponse getAllLocations() {
        List<LocationDto> locationDtos = locationRepository.findAll().stream()
                .map(entity -> new LocationDto(entity.getId(),
                        entity.getName(),
                        entity.getCountry(), entity.getCity(),
                        entity.getLocationCode())).toList();
        return new GetAllLocationsResponse(locationDtos);
    }

    @Override
    public GetLocationResponse getLocation(Long id) {
        GetLocationResponse response = new GetLocationResponse();
        locationRepository.findById(id)
                .ifPresent(locationEntity ->
                        response.setLocation(new LocationDto()));
        return response;
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdateTransportation(AddTransportationRequest request) {
        LocationEntity origin = locationRepository.findByLocationCode(request.getOrigin())
                .orElseThrow(() -> new DomainException("No Origin"));
        LocationEntity destination = locationRepository.findByLocationCode(request.getDestination())
                .orElseThrow(() -> new DomainException("No Destination"));

        TransportationEntity transportationEntity = new TransportationEntity();
        transportationEntity.setOrigin(origin);
        transportationEntity.setDestination(destination);
        transportationEntity.setTransportationType(TransportationType.valueOf(request.getTransportationType())); // todo enumCheck
        transportationRepository.save(transportationEntity);
    }

    @Override
    public GetAllTransportationsResponse getAllTransportations() {
        List<TransportationDto> transportationDtos = transportationRepository.findAll().stream()
                .map(entity -> new TransportationDto(entity.getId(),
                        mapToDtO(entity.getOrigin()),
                        mapToDtO(entity.getDestination()),
                        entity.getTransportationType())).toList();
        return new GetAllTransportationsResponse(transportationDtos);
    }

    @Override
    public GetTransportationResponse getTransportation(Long id) {
        GetTransportationResponse response = new GetTransportationResponse();
        TransportationDto transportationDto = transportationRepository.findById(id).map(
                entity ->
                        new TransportationDto(entity.getId(),
                        mapToDtO(entity.getOrigin()),
                        mapToDtO(entity.getDestination()),
                        entity.getTransportationType()))
                .orElseThrow(() -> new DomainException("No Transportation"));
        return response;
    }

    @Override
    public void deleteTransportation(Long id) {
        transportationRepository.deleteById(id);
    }

    private LocationDto mapToDtO(LocationEntity entity) {
        LocationDto dto = new LocationDto();
        dto.setId(entity.getId());
        dto.setCity(entity.getCity());
        dto.setName(entity.getName());
        dto.setCountry(entity.getCountry());
        dto.setLocationCode(entity.getLocationCode());
        return dto;
    }
}
