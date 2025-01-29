package com.example.demo.controller;

import com.example.demo.data.Request.AddLocationRequest;
import com.example.demo.data.Request.AddTransportationRequest;
import com.example.demo.data.Response.GetAllLocationsResponse;
import com.example.demo.data.Response.GetAllTransportationsResponse;
import com.example.demo.service.PlacementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/placementService")
public class PlacementController {

    private final PlacementService placementService;

    public PlacementController(PlacementService placementService) {
        this.placementService = placementService;
    }

    @PostMapping("/addOrUpdateLocation")
    public ResponseEntity<Boolean> addLocation(
            @RequestBody AddLocationRequest request) {
        placementService.saveOrUpdateLocation(request.getLocation());
        return ResponseEntity.ok(true);
    }

    @GetMapping("/getAllLocations")
    public ResponseEntity<GetAllLocationsResponse> getLocations() {
        return ResponseEntity.ok(placementService.getAllLocations());
    }

    @DeleteMapping("/deleteLocation")
    public ResponseEntity<Boolean> deleteLocation(
            @RequestParam Long locationId) {
        placementService.deleteLocation(locationId);
        return ResponseEntity.ok(true);
    }


    @PostMapping("/addOrUpdateTransportation")
    public ResponseEntity<Boolean> addTransportation(
            @RequestBody AddTransportationRequest request) {
        placementService.saveOrUpdateTransportation(request);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/getAllTransportations")
    public ResponseEntity<GetAllTransportationsResponse> getTransportations() {
        return ResponseEntity.ok(placementService.getAllTransportations());
    }

    @DeleteMapping("/deleteTransportation")
    public ResponseEntity<Boolean> deleteTransportation(
            @RequestParam Long transportationId) {
        placementService.deleteTransportation(transportationId);
        return ResponseEntity.ok(true);
    }
}

