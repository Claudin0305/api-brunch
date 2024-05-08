package com.brunch.api.controller;

import com.brunch.api.entity.Event;
import com.brunch.api.entity.ImageData;
import com.brunch.api.repository.StorageImageRepository;
import com.brunch.api.service.classes.EventServiceImplement;
import com.brunch.api.service.classes.StorageImageService;
import com.brunch.api.utils.EventRegisterRequest;
import com.brunch.api.utils.FileStorageProperties;
import com.brunch.api.utils.UploadPhotoRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
//@ControllerAdvice
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventServiceImplement eventServiceImplement;
    @Autowired
    private StorageImageService storageImageService;
    @Autowired
    private StorageImageRepository storageImageRepository;
    private final Path fileStorageLocation;

    public EventController(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
    }
//    @Autowired
//    private EventImageService eventImageService;
//@Autowired
//private EventImageRepository eventImageRepository;

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok().body(eventServiceImplement.getAllEvents());
    }

    @GetMapping("/{id_event}")
    public Event getEventById(@PathVariable Long id_event) {
        return eventServiceImplement.getEventById(id_event);
    }

    //    @PostMapping
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Event> createEvent(@Valid @ModelAttribute EventRegisterRequest eventRegisterRequest, @RequestPart("file") MultipartFile file) throws IOException {

//        System.out.println(file.isEmpty());
//        if (file.isEmpty()) {
//            return ResponseEntity.badRequest().build();
//        }
        Event e = new Event();
        e.setAdr_email_event(eventRegisterRequest.getAdr_email_event());
        e.setEventType(eventRegisterRequest.getEventType());
        e.setFormat_event(eventRegisterRequest.getFormat_event());
        e.setDate_debut(eventRegisterRequest.getDate_debut());
        e.setDate_fin(eventRegisterRequest.getDate_fin());
        e.setUrl(eventRegisterRequest.getUrl());
        e.setCible_participation(eventRegisterRequest.getCible_participation());
        e.setText_descriptif(eventRegisterRequest.getText_descriptif());
        e.setHeure_debut(eventRegisterRequest.getHeure_debut());
        e.setHeure_fin(eventRegisterRequest.getHeure_fin());

        Event createEvent = eventServiceImplement.createEvent(e);
//        updolad(file, createEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(createEvent);
    }

    private void upload(MultipartFile file, Event event) throws IOException {

        String fileName = generateUniqueFileName(file.getOriginalFilename());
        System.out.print(fileName);
//        try {
        createDirectories(fileStorageLocation);

        Path targetLocation = fileStorageLocation.resolve(fileName);

        file.transferTo(targetLocation);
        ImageData fileEntity = new ImageData();
        fileEntity.setName(fileName);
        fileEntity.setType(file.getContentType());
        fileEntity.setEvent__(event);
        storageImageService.createFile(fileEntity);

//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return ResponseEntity.badRequest().body("File upload failed.");
//        }


    }

    @PutMapping("/{id_event}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Event> updateEvent(@PathVariable(name = "id_event") Long id_event, @ModelAttribute Event event, @RequestPart(value = "image", required = false) MultipartFile file, @RequestParam(value = "image_change") String image_change) throws IOException {
//        System.out.println(file);
//        if (file.isEmpty()) {
//            System.out.println(file);
//            return ResponseEntity.badRequest().build();
//        }
        Event updateEvent = eventServiceImplement.updateEvent(id_event, event);
        List<ImageData> imageDataList = new ArrayList<>();
        for (ImageData img : storageImageService.findAll()) {
            if (img.getEvent__().getId_event() == id_event) {
                imageDataList.add(img);
            }
        }
        if (image_change.equalsIgnoreCase("1")) {
            for (ImageData imageData : imageDataList) {
                if (imageData.isActive()) {
                    imageData.setActive(false);
                    storageImageRepository.save(imageData);
                }
            }
//            updolad(file, updateEvent);
        }
        return ResponseEntity.ok(updateEvent);
    }

    @DeleteMapping("/{id_event}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id_event) {
        eventServiceImplement.deleteEvent(id_event);
        return ResponseEntity.ok().build();
    }


//    @PostMapping("/images")
//    public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image") MultipartFile file, Event event) throws IOException {
//        String uploadImage = eventImageService.uploadImageToFileSystem(file, event);
//        return  ResponseEntity.status(HttpStatus.OK).body(uploadImage);
//    }
//    @GetMapping("/images/{fileName}")
//    public ResponseEntity<?> downloadImageToFileSystem(@PathVariable String fileName) throws IOException {
//        byte[] imageData = eventImageService.downloadImageFromFileSystem(fileName);
//        EventImage eventImage = eventImageService.findByName(fileName);
//        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(eventImage.getType())).body(imageData);
//    }

    //    @GetMapping("/{name}/image")
//    public ResponseEntity<Resource> getImage(@PathVariable String name) {
//        // Retrieve the product from the database
//        Optional<EventImage> eventImage = productRepository.findById(productId);
//        if (optionalProduct.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Product product = optionalProduct.get();
//
//        // Create a Resource object to represent the image file
//        Resource imageResource = new FileSystemResource(product.getImagePath());
//
//        // Check if the file exists
//        if (!imageResource.exists()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // Set the appropriate content type for the image
//        String contentType;
//        try {
//            contentType = Files.probeContentType(Paths.get(product.getImagePath()));
//        } catch (IOException e) {
//            contentType = "application/octet-stream";
//        }
//
//        // Return the image file as a response
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .body(imageResource);
//    }
    @GetMapping("/images/{name}")
    public ResponseEntity<?> downloadImage(@PathVariable String name) {
        ImageData fileEntity = storageImageService.findByName(name);
        if (fileEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fichier introuvable");
        }
        try {
            Path filePath = fileStorageLocation.resolve(name).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .contentType(MediaType.IMAGE_JPEG)
                    .contentType(MediaType.IMAGE_GIF)
                    .body(resource);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fichier introuvable introuvable");
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    private String generateUniqueFileName(String originalFileName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String randomString = UUID.randomUUID().toString().substring(0, 16);
        return timestamp + "-" + randomString + getExtension(originalFileName);
    }

    private void createDirectories(Path path) throws IOException {
        if (!Files.isDirectory(path)) {
            Files.createDirectories(path);
        }
    }

    public static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<?> uploadImage(@ModelAttribute UploadPhotoRequest uploadPhotoRequest) throws IOException {
//        @RequestPart(value="file") MultipartFile file
//        System.out.println(eventRegisterRequest.getEventType());
//        System.out.println(file.isEmpty());
//        System.out.println(file.getOriginalFilename());

        System.out.println("Testttt1");
//        System.out.println(eventRegisterRequest.getDate_debut());
//        System.out.println(eventRegisterRequest.getFile());
        System.out.println("Testttt2");
        Event e = eventServiceImplement.getEventById(uploadPhotoRequest.getEvent_id());
        upload(uploadPhotoRequest.getFile(), e);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success upload");
    }
}
