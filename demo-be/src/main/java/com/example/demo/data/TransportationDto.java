package com.example.demo.data;

import com.example.demo.domain.TransportationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransportationDto {
    private long id;
    private LocationDto origin;
    private LocationDto destination;
    private TransportationType transportationType;
}
