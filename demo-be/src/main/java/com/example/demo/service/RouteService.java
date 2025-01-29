package com.example.demo.service;

import com.example.demo.data.Response.GetAllTransportationsResponse;
import com.example.demo.data.Response.GetRoutesResponse;

public interface RouteService {

    GetRoutesResponse getRoutes(Long startLocation, Long endLocation);

}
