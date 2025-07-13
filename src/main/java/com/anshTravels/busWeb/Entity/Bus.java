package com.anshTravels.busWeb.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Bus {
    @Id
    private String busNumber;

    private String driverName;
    private String driverContact;

    public Bus(){

    }
    public Bus(String busNumber, String driverName, String driverContact){
        this.busNumber = busNumber;
        this.driverName = driverName;
        this.driverContact = driverContact;
    }

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
}
