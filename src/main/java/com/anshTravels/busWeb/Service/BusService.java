package com.anshTravels.busWeb.Service;

import com.anshTravels.busWeb.Entity.Bus;

import java.util.ArrayList;
import java.util.List;

public class BusService {
    List<Bus> BusList = new ArrayList<>();

    BusService(){
        BusList.add(new Bus("MH12AB1234","Rajesh Patil","9876543210"));
        BusList.add(new Bus("DL01CD5678","Anil Kumar","9123456789"));
    }

    //add
    public Bus addBus(Bus bus1){
        BusList.add(bus1);
        //db mein save krne ka logic
        return bus1;
    }
    //all
    public List<Bus> allBus(){
        //db mein save krne ka logic
        return this.BusList;
    }
    //get
    public Bus get(String busNumber){
        Bus bus1 = BusList.stream()
                .filter(bus -> bus.getBusNumber().equals(busNumber))
                .findFirst()
                .get();

        return bus1;
    }
    //delete
    public void delete(String busNumber) {
        List<Bus> list = this.BusList.stream()
                        .filter(bus -> !bus.getBusNumber().equals(busNumber))
                        .toList();

        this.BusList = list;
    }
}
