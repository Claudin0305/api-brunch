package com.brunch.api.controller;


import com.brunch.api.entity.Affiliation;
import com.brunch.api.entity.Civilite;
import com.brunch.api.service.classes.AffiliationServiceImpl;
import com.brunch.api.service.classes.CiviliteServiceImplement;
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
@RequestMapping("/api/affiliations")
public class AffiliationController {
   @Autowired
    private AffiliationServiceImpl affiliationService;


    @GetMapping
    public List<Affiliation> getAllAffiliations(){
        return affiliationService.getAllAffiliations();
    }
    @GetMapping("/{affiliationId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Affiliation getAffiliationById(@PathVariable Long affiliationId){
        return affiliationService.getAffiliationById(affiliationId);
    }
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Affiliation> createAffiliation(@Valid @RequestBody Affiliation affiliation){


        Affiliation createAffiliation = affiliationService.createAffiliation(affiliation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createAffiliation);
    }

    @PostMapping("/from-client")
    public ResponseEntity<Affiliation> createAffiliationFromClient(@Valid @RequestBody Affiliation affiliation){


        Affiliation createAffiliation = affiliationService.createAffiliation(affiliation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createAffiliation);
    }

    @PutMapping("/{affiliationId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Affiliation> updateAffiliation(@PathVariable Long affiliationId, @RequestBody Affiliation affiliation){
        Affiliation updateAffiliation = affiliationService.updateAffiliation(affiliationId, affiliation);
        return  ResponseEntity.ok(updateAffiliation);
    }

    @DeleteMapping("/{affiliationId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteAffiliation(@PathVariable Long affiliationId){
        affiliationService.deleteAffiliation(affiliationId);
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
