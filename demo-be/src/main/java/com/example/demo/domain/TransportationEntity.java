package com.example.demo.domain;

import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TRANSPORTATION",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"ORIGIN_ID", "DESTINATION_ID", "TRANSPORTATION_TYPE"}))
public class TransportationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORIGIN_ID", nullable = false)
    private LocationEntity origin;

    @ManyToOne
    @JoinColumn(name = "DESTINATION_ID", nullable = false)
    private LocationEntity destination;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSPORTATION_TYPE", nullable = false)
    private TransportationType transportationType;
}
