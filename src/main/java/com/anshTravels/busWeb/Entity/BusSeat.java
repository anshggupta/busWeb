package com.anshTravels.busWeb.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "bus_seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bus_schedule_id")
    private BusSchedule busSchedule;


    @Enumerated(EnumType.STRING)
    private CoachType coachType;

    private Integer totalSeats;

    private Integer availableSeats;

    private BigDecimal price;


}
