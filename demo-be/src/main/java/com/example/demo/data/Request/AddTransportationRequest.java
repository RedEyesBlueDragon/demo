package com.example.demo.data.Request;

import com.example.demo.data.LocationDto;
import com.example.demo.data.TransportationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddTransportationRequest {
    private long id;
    private String origin;
    private String destination;
    private String transportationType;
}
