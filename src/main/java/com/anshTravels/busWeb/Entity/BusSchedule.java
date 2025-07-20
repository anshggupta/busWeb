package com.anshTravels.busWeb.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bus_schedule")
public class BusSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate runDate;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    private Integer avaiableSeats;

    // kitni seat ki type :


    @OneToMany(mappedBy = "busSchedule")
    private List<BusSeat> busSeats;


    // booking
    @OneToMany(mappedBy = "busSchedule")
    private List<Booking> bookings;


}
