package com.anshTravels.busWeb.dto;

import com.anshTravels.busWeb.Entity.BusImage;

import java.time.LocalDateTime;

public record BusImageResponse(
        Long id,
        String fileName,
        String fileType,
        String fileUrl,
        String viewUrl,
        long size,
        LocalDateTime uploadTime
) {
    public static BusImageResponse from(BusImage image, String baseUrl, String busNumber) {
        return new BusImageResponse(
                image.getId(),
                image.getFileName(),
                image.getFileType(),
                baseUrl + "/" + image.getFileName(),                      // direct file URL (static resource)
                baseUrl + "/buses/" + busNumber + "/image",                  // served image via controller
                image.getSize(),
                image.getUploadTime()
        );
    }
}
