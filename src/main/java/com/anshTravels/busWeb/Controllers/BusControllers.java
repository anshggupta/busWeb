package com.anshTravels.busWeb.Controllers;

import com.anshTravels.busWeb.Entity.BusImage;
import com.anshTravels.busWeb.dto.*;
import com.anshTravels.busWeb.Service.BusImageService;
import com.anshTravels.busWeb.Service.BusService;
import org.springframework.core.io.Resource; // ✅ CORRECT
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

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
    @PostMapping("/{busNumber}/upload-image")
    public ResponseEntity<?> uploadBusImage(
            @PathVariable String busNumber,
            @RequestParam("image") MultipartFile image
    ) throws IOException {

        String contentType = image.getContentType();
        System.out.println(contentType);

        if (contentType.toLowerCase().startsWith("image")) {
            return new ResponseEntity<>(busImageService.upload(image, busNumber), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Image not uploaded", "403", false), HttpStatus.BAD_REQUEST);
        }
    }

    // Serve/Fetch image from a bus
    @GetMapping("/{busNumber}/image")
    public ResponseEntity<Resource> serveBusImage(
            @PathVariable("busNumber") String busNumber
    ) throws MalformedURLException {

        BusImageDataWithResource imageData = busImageService.loadImageByBusNo(busNumber);
        BusImage busImage = imageData.busImage();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(busImage.getFileType()))
                .body(imageData.resource());
    }

}

