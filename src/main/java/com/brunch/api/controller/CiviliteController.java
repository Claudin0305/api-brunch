package com.brunch.api.controller;

import com.brunch.api.entity.Civilite;
import com.brunch.api.service.classes.CiviliteServiceImplement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/civilites")
public class CiviliteController{
    @Autowired
    private CiviliteServiceImplement civiliteServiceImplement;


    @GetMapping
    public List<Civilite> getAllCivilites(){
        return civiliteServiceImplement.getAllCivilites();
    }
    @GetMapping("/{id_civilite}")
    public Civilite getCiviliteById(@PathVariable Long id_civilite){
        return civiliteServiceImplement.getCiviliteById(id_civilite);
    }
    @PostMapping
    public ResponseEntity<Civilite> createCivilite(@Valid @RequestBody Civilite civilite){


        Civilite createCivilite = civiliteServiceImplement.createCivilite(civilite);
        return ResponseEntity.status(HttpStatus.CREATED).body(createCivilite);
    }

    @PutMapping("/{id_civilite}")
    public ResponseEntity<Civilite> updateCivilite(@PathVariable Long id_civilite, @RequestBody Civilite civilite){
        Civilite updateCivilite = civiliteServiceImplement.updateCivilite(id_civilite, civilite);
        return  ResponseEntity.ok(updateCivilite);
    }

    @DeleteMapping("/{id_civilite}")
    public ResponseEntity<?> deleteCivilite(@PathVariable Long id_civilite){
        civiliteServiceImplement.deleteCivilite(id_civilite);
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
