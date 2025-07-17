package com.anshTravels.busWeb.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class BusDto {

    @NotEmpty(message = "Bus number is required!")
    @Size(min = 3, max = 20, message = "Invalid length of bus number.")
    @Pattern(regexp = "^\\d+$", message = "Bus number must contain only digits.")
    @Id
    private String busNumber;

    @NotEmpty(message = "Driver name is required.")
    @Pattern(regexp = "^[A-Za-z][A-Za-z -]*[A-Za-z]$", message = "Invalid driver name. Only letters, spaces, and hyphens are allowed.")
    private String driverName;

    @NotEmpty(message = "Driver contact is required.")
    @Pattern(regexp = "\\d{10}", message = "Driver contact must be a 10-digit number.")
    private String driverContact;


    // Getters and Setters

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverContact() {
        return driverContact;
    }

    public void setDriverContact(String driverContact) {
        this.driverContact = driverContact;
    }

}

