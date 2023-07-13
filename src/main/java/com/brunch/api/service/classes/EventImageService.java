package com.brunch.api.service.classes;

import com.brunch.api.entity.Event;
import com.brunch.api.entity.EventImage;
import com.brunch.api.repository.EventImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventImageService {
    private final String PATH_FOLDER = "C:\\Users\\tschn\\Documents\\Code\\Brunch\\files\\";
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
    @Autowired
    private EventImageRepository eventImageRepository;
    public static String generateUniqueFileName(String originalFileName) {
        String extension = StringUtils.getFilenameExtension(originalFileName);
        String uniqueFileName = generateUniqueName() + "." + extension;
        return uniqueFileName;
    }
    private static String generateUniqueName() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return timestamp + uuid;
    }

    public String uploadImageToFileSystem(MultipartFile file, Event event) throws IOException {
        String uniqueName = generateUniqueFileName(file.getOriginalFilename());
//        String filePath = PATH_FOLDER + uniqueName;
        String filePath = fileStorageLocation.toString() + uniqueName;
        EventImage eventImage =  eventImageRepository.save(EventImage.builder()
                .name(uniqueName)
                .type(file.getContentType())
                .active(true)
                .event__(event)
                .filePath(filePath).build()
        );
        file.transferTo(new File(filePath));
        if(eventImage != null){
            return "Image upload successfully: " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<EventImage> eventImage = eventImageRepository.findByName(fileName);
        String filePath =  eventImage.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    public EventImage findByName(String fileName){
        return  eventImageRepository.findEventImageByName(fileName);
    }
   public List<EventImage> findAll(){
        return eventImageRepository.findAll();
   }
}
