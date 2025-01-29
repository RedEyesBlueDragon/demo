package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteDto {
    private TransportationDto preFlight;
    private TransportationDto flight;
    private TransportationDto afterFlight;

}
