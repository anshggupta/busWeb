package com.anshTravels.busWeb.Service;

import com.anshTravels.busWeb.Config.AppConstants;
import com.anshTravels.busWeb.dto.BusImageDataWithResource;
import com.anshTravels.busWeb.dto.BusImageResponse;
import com.anshTravels.busWeb.Entity.Bus;
import com.anshTravels.busWeb.Entity.BusImage;
import com.anshTravels.busWeb.Exceptions.ResourceNotFoundException;
import com.anshTravels.busWeb.Repository.BusImageRepository;
import com.anshTravels.busWeb.Repository.BusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class BusImageService {

    @Value("${bus.image.folder.path}")
    private String folderPath;

    @Autowired
    private BusRepo busRepository;

    @Autowired
    private BusImageRepository busImageRepository;

    public BusImageResponse upload(MultipartFile file, String busNumber) throws IOException {

        Bus bus = busRepository.findById(busNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found !!"));

        // Check and create directory if not exists
        if (!Files.exists(Paths.get(folderPath))) {
            System.out.println("Creating folder at: " + folderPath);
            Files.createDirectories(Paths.get(folderPath));
        }

        String fullFilePath = folderPath + UUID.randomUUID() + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(fullFilePath), StandardCopyOption.REPLACE_EXISTING);

        System.out.println("File uploaded: " + fullFilePath);

        // Create and set image metadata
        BusImage busImage = new BusImage();
        busImage.setFileName(fullFilePath);
        busImage.setFileType(file.getContentType());
        busImage.setSize(file.getSize());

        // Set relationships
        busImage.setBus(bus);
        bus.setBusImage(busImage);

        // Save bus with image
        Bus savedBus = busRepository.save(bus);

        return BusImageResponse.from(savedBus.getBusImage(), AppConstants.BASE_URL, busNumber);
    }


    public BusImageDataWithResource loadImageByBusNo(String busNumber) throws MalformedURLException {

        // Get the bus using bus ID
        Bus bus = busRepository.findById(busNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found!!"));

        BusImage busImage = bus.getBusImage();
        if (busImage == null) {
            throw new ResourceNotFoundException("Image not found !!");
        }

        Path path = Paths.get(busImage.getFileName());

        if (!Files.exists(path)) {
            throw new ResourceNotFoundException("Image file not found on disk !!");
        }

        UrlResource urlResource = new UrlResource(path.toUri());

        // Package image entity + resource together
        return new BusImageDataWithResource(busImage, urlResource);
    }

}
