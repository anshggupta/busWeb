package com.anshTravels.busWeb.Controllers;

import com.anshTravels.busWeb.Entity.Bus;
import com.anshTravels.busWeb.Service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bus")
public class BusControllers {

    @Autowired
    private BusService busService;
    //all
    @GetMapping("/all-buses")
    public List<Bus> allBuses(){
        return busService.all();
    }

//    get
    @GetMapping("/get-bus/{busNumber}")
    public Bus getBus(@PathVariable String busNumber){
        return busService.get(busNumber);
    }
//    add
    @PostMapping("/add-bus")
    public Bus addBus(@RequestBody Bus bus){
        return busService.add(bus);
    }
//    delete
    @DeleteMapping("/delete-bus/{busNumber}")
    public void deleteBus (@PathVariable String busNumber){
        busService.delete(busNumber);
    }
}

