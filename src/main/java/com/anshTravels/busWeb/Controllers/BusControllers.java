package com.anshTravels.busWeb.Controllers;

import com.anshTravels.busWeb.Entity.Bus;
import com.anshTravels.busWeb.Exceptions.ResourceNotFoundException;
import com.anshTravels.busWeb.Service.BusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bus")
public class BusControllers {

    @Autowired
    private BusService busService;
    //all
    @GetMapping("/all-buses")
    public ResponseEntity<List<Bus>> allBuses(){
        return new ResponseEntity<>(busService.all(), HttpStatus.OK);
    }

    //    get
    @GetMapping("/get-bus/{busNumber}")
    public ResponseEntity<Bus> getBus(@PathVariable String busNumber){
        Bus bus = busService.get(busNumber);
        if (bus == null) {
            throw new ResourceNotFoundException("Bus not found with number: " + busNumber);
        }
        return new ResponseEntity<>(bus, HttpStatus.OK);
    }

     //    add
     @PostMapping("/add-bus")
     public ResponseEntity<Bus> addBus(@RequestBody Bus bus) {
         Bus newBus = busService.add(bus);
         return new ResponseEntity<>(newBus, HttpStatus.CREATED);
     }

     //    delete
     @DeleteMapping("/delete-bus/{busNumber}")
     public ResponseEntity<String> deleteBus(@PathVariable String busNumber) {
         Bus bus = busService.get(busNumber);
         if (bus == null) {
             throw new ResourceNotFoundException("Bus not found with number: " + busNumber);
         }
         busService.delete(busNumber);
         return new ResponseEntity<>("Bus with number " + busNumber + " deleted successfully", HttpStatus.OK);
     }
}

