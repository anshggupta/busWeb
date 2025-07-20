package com.anshTravels.busWeb.Service.impl;

import com.anshTravels.busWeb.Entity.Station;
import com.anshTravels.busWeb.Exceptions.ResourceNotFoundException;
import com.anshTravels.busWeb.Repository.StationRepo;
import com.anshTravels.busWeb.Service.StationService;
import com.anshTravels.busWeb.dto.PagedResponse;
import com.anshTravels.busWeb.dto.StationDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService {

    private StationRepo stationRepo;

    private ModelMapper modelMapper;

    public StationServiceImpl(StationRepo stationRepo, ModelMapper modelMapper) {
        this.stationRepo = stationRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public StationDto createStation(StationDto stationDto) {
        Station station = modelMapper.map(stationDto, Station.class);
        Station savedStation = stationRepo.save(station);
        return modelMapper.map(savedStation, StationDto.class);
    }

    @Override
    public PagedResponse<StationDto> listStations(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.trim().toLowerCase().equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Station> stations = stationRepo.findAll(pageable);
        Page<StationDto> stationDtos = stations.map(station -> modelMapper.map(station, StationDto.class));
        return PagedResponse.fromPage(stationDtos);
    }

    @Override
    public StationDto getById(Long id) {
        Station station = stationRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("station not found with given id !"));
        return modelMapper.map(station, StationDto.class);
    }

    @Override
    public StationDto update(Long id, StationDto dto) {
        Station station = stationRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("station not found with given id !"));
        //update details
        station.setName(dto.getName());
        station.setCity(dto.getCity());
        station.setCode(dto.getCode());
        station.setState(dto.getState());
        Station updatedStaion = stationRepo.save(station);
        return modelMapper.map(updatedStaion, StationDto.class);



    }

    @Override
    public void delete(Long id) {
        Station station = stationRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("station not found with given id !"));

        stationRepo.delete(station);
    }
}
