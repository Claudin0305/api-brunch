package com.brunch.api.controller;

import com.brunch.api.entity.TrancheAge;
import com.brunch.api.service.classes.TranchesAgeServiceImplement;
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
@RequestMapping("/api/tranche-ages")
public class TranchesAgeController {
    @Autowired
    private TranchesAgeServiceImplement tranchesAgeServiceImplement;


    @GetMapping
    public List<TrancheAge> getAllTranchesAges(){
        return tranchesAgeServiceImplement.getAllTranchesAges();
    }
    @GetMapping("/{id_tranche_age}")
    public TrancheAge getTranchesAgeById(@PathVariable Long id_tranche_age){
        return tranchesAgeServiceImplement.getTrancheAgeById(id_tranche_age);
    }
    @PostMapping
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<TrancheAge> createTranchesAge(@Valid @RequestBody TrancheAge trancheAge){


        TrancheAge createTrancheAge = tranchesAgeServiceImplement.createTranchesAge(trancheAge);
        return ResponseEntity.status(HttpStatus.CREATED).body(createTrancheAge);
    }

    @PutMapping("/{id_tranche_age}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<TrancheAge> updateTranchesAge(@PathVariable Long id_tranche_age, @RequestBody TrancheAge trancheAge){
        TrancheAge updateTrancheAge = tranchesAgeServiceImplement.updateTranchesAge(id_tranche_age, trancheAge);
        return  ResponseEntity.ok(updateTrancheAge);
    }

    @DeleteMapping("/{id_tranche_age}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteTranchesAge(@PathVariable Long id_tranche_age){
       tranchesAgeServiceImplement.deleteTranchesAge(id_tranche_age);
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
