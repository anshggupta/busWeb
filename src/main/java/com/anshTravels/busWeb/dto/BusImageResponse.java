package com.anshTravels.busWeb.dto;

import com.anshTravels.busWeb.Entity.BusImage;

import java.time.LocalDateTime;

public record BusImageResponse(
        Long id,
        String fileName,
        String fileType,
        String url,
        long size,
        LocalDateTime uploadTime
) {

    public static BusImageResponse from(BusImage image, String baseUrl) {
        return new BusImageResponse(
                image.getId(),
                image.getFileName(),
                image.getFileType(),
                baseUrl + "/" + image.getFileName(),
                image.getSize(),
                image.getUploadTime()
        );
    }
}
