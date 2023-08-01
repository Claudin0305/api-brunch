package com.brunch.api.controller;

import com.brunch.api.entity.Event;
import com.brunch.api.entity.ImageData;
import com.brunch.api.repository.StorageImageRepository;
import com.brunch.api.service.classes.EventServiceImplement;
import com.brunch.api.service.classes.StorageImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@ControllerAdvice
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventServiceImplement eventServiceImplement;
    @Autowired
    private StorageImageService storageImageService;
    @Autowired
    private StorageImageRepository storageImageRepository;

//    @Autowired
//    private EventImageService eventImageService;
//@Autowired
//private EventImageRepository eventImageRepository;

    @GetMapping
    public List<Event> getAllEvents(){
        return eventServiceImplement.getAllEvents();
    }
    @GetMapping("/{id_event}")
    public Event getEventById(@PathVariable Long id_event){
        return eventServiceImplement.getEventById(id_event);
    }
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Event> createEvent(@Valid @ModelAttribute Event event, @RequestPart(value = "image") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Event createEvent = eventServiceImplement.createEvent(event);
        String imageData = storageImageService.uploadImage(file, createEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(createEvent);
    }

    @PutMapping("/{id_event}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Event> updateEvent(@PathVariable(name = "id_event") Long id_event, @ModelAttribute Event event, @RequestPart(value = "image", required = false) MultipartFile file, @RequestParam(value="image_change") String image_change) throws IOException {
        Event updateEvent = eventServiceImplement.updateEvent(id_event, event);
        List<ImageData> imageDataList = new ArrayList<>();
        for(ImageData img: storageImageService.findAll()){
            if(img.getEvent__().getId_event() == id_event){
                imageDataList.add(img);
            }
        }
        if(image_change.equals('1')){
            for(ImageData imageData : imageDataList){
                if(imageData.isActive()){
                    imageData.setActive(false);
                    storageImageRepository.save(imageData);
                }
            }
//            String uploadImage = eventImageService.saveImage(updateEvent, file);
        }
        return  ResponseEntity.ok(updateEvent);
    }

    @DeleteMapping("/{id_event}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id_event){
        eventServiceImplement.deleteEvent(id_event);
        return  ResponseEntity.ok().build();
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
    public ResponseEntity<?> downloadImage(@PathVariable String name){
        byte[] imageData = storageImageService.downloadImage(name);
        ImageData img = storageImageService.download(name);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(img.getType())).body(imageData);
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

}
