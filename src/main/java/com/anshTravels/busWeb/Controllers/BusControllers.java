package com.anshTravels.busWeb.Controllers;

import com.anshTravels.busWeb.Entity.Bus;
import com.anshTravels.busWeb.Service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;


@RestController
@RequestMapping("/")
public class BusControllers {

    @Autowired
    private BusService busService;
    //all
    @GetMapping("/buses")
    public List<Bus> allBuses(){
        return busService.allBus();
    }

//    get
    @GetMapping("/{busNumber}")
    public Bus getBus(@PathVariable String busNumber){
        return busService.get(busNumber);
    }
//    add
    @PostMapping("/addBus")
    public Bus addBus(@RequestBody Bus bus){
        return busService.addBus(bus);
    }
//    delete
    @DeleteMapping("/{busNumber}")
    public void deleteBus (@PathVariable String busNumber){
        busService.delete(busNumber);
    }
}

