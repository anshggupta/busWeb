package com.anshTravels.busWeb.Entity;

import com.anshTravels.busWeb.Annotaion.ValidBusNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bus {

    @Id
    @NotEmpty(message = "Bus number must not be empty")
    @ValidBusNumber
    private String busNumber;

    @NotBlank(message = "Driver name is required")
    private String driverName;

    @NotBlank(message = "Driver contact is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Driver contact must be a valid 10-digit Indian mobile number")
    private String driverContact;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private BusImage busImage;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<BusRoute> busRoute;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<BusSchedule> busSchedule;


    // Getters and Setters

}
