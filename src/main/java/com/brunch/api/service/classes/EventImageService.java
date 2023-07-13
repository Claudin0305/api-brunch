package com.brunch.api.service.classes;

import com.brunch.api.entity.Event;
import com.brunch.api.entity.EventImage;
import com.brunch.api.repository.EventImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class EventImageService {
    @Autowired
    private EventImageRepository eventImageRepository;
    private final Path fileStorageLocation;

    @Autowired
    public EventImageService(Environment env) {
        this.fileStorageLocation = Paths.get(env.getProperty("app.file.upload-dir", "./uploads/files"))
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException(
                    "Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public EventImage saveImage(Event event, MultipartFile file){
        try {
            // Generate a unique file name
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            // Create the target file path
            String filePath = fileStorageLocation.toString() + File.separator + fileName;

            // Save the file to the target path
            File targetFile = new File(filePath);
            file.transferTo(targetFile);

            // Create the Product entity and set the file path
            EventImage eventImage = new EventImage();
            eventImage.setPath(filePath);
            eventImage.setName(fileName);
            eventImage.setActive(true);
            eventImage.setEvent__(event);

            // Save the product entity
            return eventImageRepository.save(eventImage);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save the product: " + e.getMessage());
        }
    }
    public List<EventImage> findAll(){
        Sort sort = Sort.by("createdAt").descending();
        return eventImageRepository.findAll(sort);
    }
}
