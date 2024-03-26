package com.brunch.api.controller;


import com.brunch.api.entity.Affiliation;
import com.brunch.api.entity.Civilite;
import com.brunch.api.entity.PaiementRepas;
import com.brunch.api.service.classes.AffiliationServiceImpl;
import com.brunch.api.service.classes.CiviliteServiceImplement;
import com.brunch.api.service.classes.PaiementRepasServiceImpl;
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
@RequestMapping("/api/paiement-repas")
public class PaiementRepasController {
    @Autowired
    private PaiementRepasServiceImpl paiementRepasService;


    @GetMapping
    public List<PaiementRepas> getAllPaiementRepas(){
        return paiementRepasService.getAllPaiementRepas();
    }
    @GetMapping("/{paiementRepasId}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public PaiementRepas getPaiementRepasById(@PathVariable Long paiementRepasId){
        return paiementRepasService.getPaiementRepasById(paiementRepasId);
    }
    @PostMapping
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<PaiementRepas> createPaiementRepas(@Valid @RequestBody PaiementRepas paiementRepas){


        PaiementRepas createPaiementRepas = paiementRepasService.createPaiementRepas(paiementRepas);
        return ResponseEntity.status(HttpStatus.CREATED).body(createPaiementRepas);
    }



    @PutMapping("/{paiementRepasId}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<PaiementRepas> updatePaiementRepas(@PathVariable Long paiementRepasId, @RequestBody PaiementRepas paiementRepas){

        PaiementRepas updatePaiementRepas = paiementRepasService.updatePaiementRepas(paiementRepasId, paiementRepas);
        return  ResponseEntity.ok(updatePaiementRepas);
    }

    @DeleteMapping("/{paiementRepasId}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deletePaiementRepas(@PathVariable Long paiementRepasId){
        paiementRepasService.deletePaiementRepas(paiementRepasId);
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
