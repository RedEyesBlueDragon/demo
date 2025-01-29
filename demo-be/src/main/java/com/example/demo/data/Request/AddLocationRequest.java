package com.example.demo.data.Request;

import com.example.demo.data.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddLocationRequest {
    private LocationDto location;
}
