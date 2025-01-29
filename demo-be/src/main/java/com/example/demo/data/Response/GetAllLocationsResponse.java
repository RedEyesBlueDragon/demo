package com.example.demo.data.Response;

import com.example.demo.data.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllLocationsResponse {
    private List<LocationDto> locations;
}
