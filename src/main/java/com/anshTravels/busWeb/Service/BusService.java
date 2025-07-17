package com.anshTravels.busWeb.Service;

import com.anshTravels.busWeb.dto.BusDto;
import com.anshTravels.busWeb.dto.PagedResponse;
import com.anshTravels.busWeb.Entity.Bus;
import com.anshTravels.busWeb.Exceptions.ResourceNotFoundException;
import com.anshTravels.busWeb.Repository.BusRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepo busRepo;

    @Autowired
    private ModelMapper modelMapper;

    // ✅ Add Bus
    public BusDto add(BusDto busDTO) {
        Bus bus = modelMapper.map(busDTO, Bus.class);
        Bus savedBus = busRepo.save(bus);
        return modelMapper.map(savedBus, BusDto.class);
    }

    // ✅ Get all with pagination + sorting
    public PagedResponse<BusDto> all(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.trim().equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Bus> busPage = busRepo.findAll(pageable);

        Page<BusDto> busDTOPage = busPage.map(bus -> modelMapper.map(bus, BusDto.class));
        return PagedResponse.fromPage(busDTOPage);
    }

    // ✅ Get single bus by busNumber
    public BusDto get(String busNumber) {
        Bus bus = busRepo.findById(busNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with number: " + busNumber));
        return modelMapper.map(bus, BusDto.class);
    }

    // ✅ Delete
    public void delete(String busNumber) {
        Bus bus = busRepo.findById(busNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with number: " + busNumber));
        busRepo.delete(bus);
    }
}
