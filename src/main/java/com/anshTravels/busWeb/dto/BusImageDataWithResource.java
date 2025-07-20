package com.anshTravels.busWeb.dto;

import com.anshTravels.busWeb.Entity.BusImage;
import org.springframework.core.io.Resource;


public record BusImageDataWithResource(
            BusImage busImage,
            Resource resource
    ) {
    }

