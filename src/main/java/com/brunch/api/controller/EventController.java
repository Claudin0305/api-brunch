package com.brunch.api.controller;

import com.brunch.api.entity.Event;
import com.brunch.api.entity.EventImage;
import com.brunch.api.repository.EventImageRepository;
import com.brunch.api.service.classes.EventImageService;
import com.brunch.api.service.classes.EventServiceImplement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@ControllerAdvice
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventServiceImplement eventServiceImplement;

    @Autowired
    private EventImageService eventImageService;
@Autowired
private EventImageRepository eventImageRepository;

    @GetMapping
    public List<Event> getAllEvents(){
        return eventServiceImplement.getAllEvents();
    }
    @GetMapping("/{id_event}")
    public Event getEventById(@PathVariable Long id_event){
        return eventServiceImplement.getEventById(id_event);
    }
    @PostMapping
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Event> createEvent(@Valid @ModelAttribute Event event, @RequestPart(value = "image") MultipartFile file) throws IOException {
        Event createEvent = eventServiceImplement.createEvent(event);
        String uploadImage = eventImageService.uploadImageToFileSystem(file, createEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(createEvent);
    }

    @PutMapping
//    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Event> updateEvent(@RequestParam(name = "id_event") Long id_event, @ModelAttribute Event event, @RequestPart(value = "image", required = false) MultipartFile file, @RequestParam(value="image_change") String image_change) throws IOException {
        Event updateEvent = eventServiceImplement.updateEvent(id_event, event);
        List<EventImage> eventImages = new ArrayList<>();
        for(EventImage img: eventImageService.findAll()){
            if(img.getEvent__().getId_event() == id_event){
                eventImages.add(img);
            }
        }
        if(image_change.equals('1')){
            for(EventImage eventImage : eventImages){
                if(eventImage.isActive()){
                    eventImage.setActive(false);
                    eventImageRepository.save(eventImage);
                }
            }
            String uploadImage = eventImageService.uploadImageToFileSystem(file, updateEvent);
        }
        return  ResponseEntity.ok(updateEvent);
    }

    @DeleteMapping("/{id_event}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id_event){
        eventServiceImplement.deleteEvent(id_event);
        return  ResponseEntity.ok().build();
    }


    @PostMapping("/images")
    public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image") MultipartFile file, Event event) throws IOException {
        String uploadImage = eventImageService.uploadImageToFileSystem(file, event);
        return  ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }
    @GetMapping("/images/{fileName}")
    public ResponseEntity<?> downloadImageToFileSystem(@PathVariable String fileName) throws IOException {
        byte[] imageData = eventImageService.downloadImageFromFileSystem(fileName);
        EventImage eventImage = eventImageService.findByName(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(eventImage.getType())).body(imageData);
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
