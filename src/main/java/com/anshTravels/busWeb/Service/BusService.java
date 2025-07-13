package com.anshTravels.busWeb.Service;

import com.anshTravels.busWeb.Entity.Bus;
import com.anshTravels.busWeb.Repository.BusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepo busRepo;
    //add
    public Bus add(Bus bus1){
        busRepo.save(bus1);
        return bus1;
    }
    //all
    public List<Bus> all(){
        List<Bus> list = busRepo.findAll();
        return list;
    }
    //get
    public Bus get(String busNumber) {
        return busRepo.findById(busNumber)
                .orElseThrow(() -> new RuntimeException("Bus not found with number: " + busNumber));
    }

    public void delete(String busNumber) {
        if (!busRepo.existsById(busNumber)) {
            throw new RuntimeException("Bus not found with number: " + busNumber);
        }
        busRepo.deleteById(busNumber);
    }

}
