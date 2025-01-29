package com.example.demo.data.Response;

import com.example.demo.data.LocationDto;
import com.example.demo.data.TransportationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllTransportationsResponse {
    private List<TransportationDto> transportations;

}
