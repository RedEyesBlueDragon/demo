package com.example.demo.service;

import com.example.demo.data.LocationDto;
import com.example.demo.data.Request.AddLocationRequest;
import com.example.demo.data.Request.AddTransportationRequest;
import com.example.demo.data.Response.GetAllLocationsResponse;
import com.example.demo.data.Response.GetAllTransportationsResponse;
import com.example.demo.data.Response.GetLocationResponse;
import com.example.demo.data.Response.GetTransportationResponse;
import com.example.demo.data.TransportationDto;

public interface PlacementService {

    void saveOrUpdateLocation(LocationDto location);

    GetAllLocationsResponse getAllLocations();

    GetLocationResponse getLocation(Long id);

    void deleteLocation(Long id);

    void saveOrUpdateTransportation(AddTransportationRequest request);

    GetAllTransportationsResponse getAllTransportations();

    GetTransportationResponse getTransportation(Long id);

    void deleteTransportation(Long id);


}
