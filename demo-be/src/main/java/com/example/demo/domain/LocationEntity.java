package com.example.demo.domain;

import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LOCATION")
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "COUNTRY", nullable = false) // Fixed typo
    private String country;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "LOCATION_CODE", nullable = false, unique = true) // Renamed for clarity
    private String locationCode;
}
