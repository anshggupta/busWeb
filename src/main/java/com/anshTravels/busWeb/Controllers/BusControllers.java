package com.anshTravels.busWeb.Controllers;

import com.anshTravels.busWeb.dto.BusDto;
import com.anshTravels.busWeb.dto.PagedResponse;
import com.anshTravels.busWeb.dto.BusImageResponse;
import com.anshTravels.busWeb.Service.BusImageService;
import com.anshTravels.busWeb.Service.BusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/buses")
public class BusControllers {

    @Autowired
    private BusService busService;

    @Autowired
    private BusImageService busImageService;

    // ✅ Get all buses with pagination and sorting
    @GetMapping
    public PagedResponse<BusDto> allBuses(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortBy", defaultValue = "busNumber") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir
    ) {
        return busService.all(page, size, sortBy, sortDir);
    }

    // ✅ Get a single bus by number
    @GetMapping("/{busNumber}")
    public ResponseEntity<BusDto> getBus(@PathVariable String busNumber) {
        BusDto busDTO = busService.get(busNumber);
        return new ResponseEntity<>(busDTO, HttpStatus.OK);
    }

    // ✅ Add a new bus
    @PostMapping
    public ResponseEntity<BusDto> addBus(@Valid @RequestBody BusDto busDTO) {
        BusDto newBus = busService.add(busDTO);
        return new ResponseEntity<>(newBus, HttpStatus.CREATED);
    }

    // ✅ Delete a bus
    @DeleteMapping("/{busNumber}")
    public ResponseEntity<String> deleteBus(@PathVariable String busNumber) {
        busService.delete(busNumber);
        return new ResponseEntity<>("Bus with number " + busNumber + " deleted successfully", HttpStatus.OK);
    }

    // ✅ Upload image for a bus
    @PostMapping("/upload/{busNumber}")
    public BusImageResponse uploadBusImage(
            @PathVariable String busNumber,
            @RequestParam("image") MultipartFile image
    ) throws IOException {
        return busImageService.upload(image, busNumber);
    }
}
