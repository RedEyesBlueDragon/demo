package com.example.demo.service;

import com.example.demo.data.LocationDto;
import com.example.demo.data.Response.GetAllTransportationsResponse;
import com.example.demo.data.Response.GetRoutesResponse;
import com.example.demo.data.RouteDto;
import com.example.demo.data.TransportationDto;
import com.example.demo.domain.LocationEntity;
import com.example.demo.domain.TransportationEntity;
import com.example.demo.domain.TransportationRepository;
import com.example.demo.domain.TransportationType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private final TransportationRepository transportationRepository;

    public RouteServiceImpl(TransportationRepository transportationRepository) {
        this.transportationRepository = transportationRepository;
    }


    @Override
    public GetRoutesResponse getRoutes(Long startLocation, Long endLocation) {

        List<List<TransportationDto>> routes = new ArrayList<>();
        generateRouteDto(startLocation, routes, new ArrayList<>(), endLocation, new ArrayList<>());

        return new GetRoutesResponse(routes);
    }

    private void generateRouteDto(Long originId, List<List<TransportationDto>> routes,
                                  List<TransportationDto> currentRoute, Long destinationId,
                                  List<Long> visitedEntites) {
        if (currentRoute.size() > 3) {
            return;
        }
        List<TransportationEntity> fromStarting = transportationRepository.findAllByOriginId(originId);
        fromStarting.forEach(entity -> {
            if (visitedEntites.contains(entity.getId())) {
                return;
            }
            List<TransportationDto> newRoute = new ArrayList<>(currentRoute);
            newRoute.add(toDto(entity));
            visitedEntites.add(entity.getId());

            if (entity.getDestination().getId().equals(destinationId)) {
                if (isValidRoute(newRoute)) {
                    routes.add(newRoute);
                }

            } else {
                generateRouteDto(entity.getDestination().getId(), routes, newRoute, destinationId, new ArrayList<>(visitedEntites));
            }
        });

    }

    private boolean isValidRoute(List<TransportationDto> route) {
        if (route.isEmpty() || route.size() > 3) {
            return false;
        }

        int flight = 0;
        boolean hasFlight = false;
        int beforeFlight = 0;
        int afterFlight = 0;

        for (TransportationDto transportation : route) {
           if (transportation.getTransportationType().equals(TransportationType.FLIGHT)) {
               flight++;
               hasFlight = true;
           } else {
               if (hasFlight) {
                   afterFlight++;
               } else {
                   beforeFlight++;
               }
           }
        }

        return hasFlight && flight == 1 && beforeFlight <= 1 && afterFlight <= 1;
    }


    private TransportationDto toDto(TransportationEntity entity) {
        return new TransportationDto(
                entity.getId(),
                mapToDtO(entity.getOrigin()),
                mapToDtO(entity.getDestination()),
                entity.getTransportationType()
        );
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
