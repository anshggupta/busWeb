package com.anshTravels.busWeb.Repository;

import com.anshTravels.busWeb.Entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepo extends JpaRepository<Bus, String> {

}
