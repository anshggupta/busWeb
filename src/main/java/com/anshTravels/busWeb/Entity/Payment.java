package com.anshTravels.busWeb.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    private Booking booking;


    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private  PaymentStatus paymentStatus;

    private  String paymentMethod;

    private  String tansactionId;

    private LocalDateTime createdAt;
}
