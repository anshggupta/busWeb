package com.anshTravels.busWeb.Service;

import com.anshTravels.busWeb.dto.PagedResponse;
import com.anshTravels.busWeb.dto.StationDto;

public interface StationService {

    StationDto createStation(StationDto stationDto);

    PagedResponse<StationDto> listStations(int page, int size, String sortBy, String sortDir);

    StationDto getById(Long id);

    StationDto update(Long id, StationDto dto);

    void delete(Long id);

}
