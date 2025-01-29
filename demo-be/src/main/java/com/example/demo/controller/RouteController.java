package com.example.demo.controller;

import com.example.demo.data.Response.GetAllTransportationsResponse;
import com.example.demo.data.Response.GetRoutesResponse;
import com.example.demo.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }


    @GetMapping("/allRoutes")
    public ResponseEntity<GetRoutesResponse> getValidRoutes(
            @RequestParam Long startLocation,
            @RequestParam Long endLocation) {
        return ResponseEntity.ok(routeService.getRoutes(startLocation, endLocation));
    }
}

