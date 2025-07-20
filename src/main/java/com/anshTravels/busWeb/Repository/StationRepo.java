package com.anshTravels.busWeb.Repository;

import com.anshTravels.busWeb.Entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepo extends JpaRepository<Station, Long> {
}
