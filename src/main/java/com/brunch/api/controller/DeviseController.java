package com.brunch.api.controller;

import com.brunch.api.entity.Devise;
import com.brunch.api.service.classes.DeviseServiceImplement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@ControllerAdvice
@RequestMapping("/api/devises")
public class DeviseController {
    @Autowired
    private DeviseServiceImplement deviseServiceImplement;


    @GetMapping
    public List<Devise> getAllDevises(){
        return deviseServiceImplement.getAllDevises();
    }
    @GetMapping("/{id_devise}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Devise getDeviseById(@PathVariable Long id_devise){
        return deviseServiceImplement.getDeviseById(id_devise);
    }
    @PostMapping
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Devise> createDevise(@Valid @RequestBody Devise devise){


        Devise createDevise = deviseServiceImplement.createDevise(devise);
        return ResponseEntity.status(HttpStatus.CREATED).body(createDevise);
    }

    @PutMapping("/{id_devise}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Devise> updateDevise(@PathVariable Long id_devise, @RequestBody Devise devise){
        Devise updateDevise = deviseServiceImplement.updateDevise(id_devise, devise);
        return  ResponseEntity.ok(updateDevise);
    }

    @DeleteMapping("/{id_devise}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteDevise(@PathVariable Long id_devise){
        deviseServiceImplement.deleteDevise(id_devise);
        return  ResponseEntity.ok().build();
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
