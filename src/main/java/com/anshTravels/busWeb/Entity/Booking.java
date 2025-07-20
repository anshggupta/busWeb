package com.anshTravels.busWeb.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bus_bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User who made the booking
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String pnr;

    // Bus Schedule for the booking
    @ManyToOne
    @JoinColumn(name = "bus_schedule_id")
    private BusSchedule busSchedule;

    // Source station
    @ManyToOne
    @JoinColumn(name = "source_station_id")
    private Station sourceStation;

    // Destination station
    @ManyToOne
    @JoinColumn(name = "destination_station_id")
    private Station destinationStation;

    private LocalDate journeyDate;

    private BigDecimal totalFare;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Passengers linked to this booking
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<BookingPassenger> passengers;

    // Payment details
    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private Payment payment;

}
