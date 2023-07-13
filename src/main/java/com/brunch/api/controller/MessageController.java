package com.brunch.api.controller;

import com.brunch.api.entity.Message;
import com.brunch.api.service.classes.MessageServiceImplement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@ControllerAdvice
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageServiceImplement messageServiceImplement;

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages(){
        return ResponseEntity.ok(messageServiceImplement.getAllMessages());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable Long id){
        return ResponseEntity.ok(messageServiceImplement.getMessageById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Message>create(@Valid @ModelAttribute Message message){
        Message createMessage = messageServiceImplement.createMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(createMessage);
    }

    @PutMapping( "/{id}")
    public ResponseEntity<Message>update(@Valid @ModelAttribute Message message, @PathVariable Long id){
        Message updateMEssage = messageServiceImplement.updateMessage(id, message);
        return  ResponseEntity.ok(updateMEssage);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePays(@PathVariable Long id){
        messageServiceImplement.deleteMessage(id);
        return  ResponseEntity.ok().build();
    }
}
