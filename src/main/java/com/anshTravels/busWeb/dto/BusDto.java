package com.anshTravels.busWeb.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusDto {

    @NotEmpty(message = "Bus number is required!")
    @Size(min = 3, max = 20, message = "Invalid length of bus number.")
    @Id
    private String busNumber;

    @NotEmpty(message = "Driver name is required.")
    @Pattern(regexp = "^[A-Za-z][A-Za-z -]*[A-Za-z]$", message = "Invalid driver name. Only letters, spaces, and hyphens are allowed.")
    private String driverName;

    @NotEmpty(message = "Driver contact is required.")
    @Pattern(regexp = "\\d{10}", message = "Driver contact must be a 10-digit number.")
    private String driverContact;




}

