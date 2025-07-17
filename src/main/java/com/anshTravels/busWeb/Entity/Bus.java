package com.anshTravels.busWeb.Entity;

import com.anshTravels.busWeb.Annotaion.ValidBusNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
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

    public Bus() {}

    public Bus(String busNumber, String driverName, String driverContact) {
        this.busNumber = busNumber;
        this.driverName = driverName;
        this.driverContact = driverContact;
    }

    // Getters and Setters
    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getDriverContact() {
        return driverContact;
    }

    public void setDriverContact(String driverContact) {
        this.driverContact = driverContact;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public BusImage getBusImage() {
        return busImage;
    }

    public void setBusImage(BusImage busImage) {
        this.busImage = busImage;
    }
}
