package com.anshTravels.busWeb.Controllers.Admin;

import com.anshTravels.busWeb.Service.BusImageService;
import com.anshTravels.busWeb.Service.BusService;
import com.anshTravels.busWeb.dto.BusDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/Buses")

public class BusController {

    @Autowired
    private BusService busService;

    @Autowired
    private BusImageService busImageService;

    // Add a new bus
    @PostMapping
    public ResponseEntity<BusDto> addBus(@Valid @RequestBody BusDto busDTO) {
        BusDto newBus = busService.add(busDTO);
        return new ResponseEntity<>(newBus, HttpStatus.CREATED);
    }



    // list

    // get detail

    //update bus

    // delete bus

}
