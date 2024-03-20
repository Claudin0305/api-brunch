package com.brunch.api.controller;


import com.brunch.api.entity.Affiliation;
import com.brunch.api.entity.Civilite;
import com.brunch.api.entity.HistoriquePaiementRepas;
import com.brunch.api.entity.PaiementRepas;
import com.brunch.api.service.classes.AffiliationServiceImpl;
import com.brunch.api.service.classes.CiviliteServiceImplement;
import com.brunch.api.service.classes.HistoriquePaiementServiceImpl;
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
@RequestMapping("/api/historique-paiement-repas")
public class HistoriquePaiementRepasController {
    @Autowired
    private HistoriquePaiementServiceImpl historiquePaiementService;


    @GetMapping
    public List<HistoriquePaiementRepas> getAllHistoriquePaiementRepas(){
        return historiquePaiementService.getAllHistoriquePaiementRepas();
    }
    @GetMapping("/{historiquePaiementRepasId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public HistoriquePaiementRepas getHistoriquePaiementRepasById(@PathVariable Long historiquePaiementRepasId){
        return historiquePaiementService.getHistoriquePaiementRepasById(historiquePaiementRepasId);
    }
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<HistoriquePaiementRepas> createHistoriquePaiementRepas(@Valid @RequestBody HistoriquePaiementRepas historiquePaiementRepas){


        HistoriquePaiementRepas createHistoriquePaiementRepas = historiquePaiementService.createHistoriquePaiementRepas(historiquePaiementRepas);
        return ResponseEntity.status(HttpStatus.CREATED).body(createHistoriquePaiementRepas);
    }



    @PutMapping("/{historiquePaiementRepasId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<HistoriquePaiementRepas> updateHistoriquePaiementRepas(@PathVariable Long historiquePaiementRepasId, @RequestBody HistoriquePaiementRepas historiquePaiementRepas){

        HistoriquePaiementRepas updateHistoriquePaiementRepas = historiquePaiementService.updateHistoriquePaiementRepas(historiquePaiementRepasId, historiquePaiementRepas);
        return  ResponseEntity.ok(updateHistoriquePaiementRepas);
    }

    @DeleteMapping("/{historiquePaiementRepasId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteHistoriquePaiementRepas(@PathVariable Long historiquePaiementRepasId){
        historiquePaiementService.deletePaiementRepas(historiquePaiementRepasId);
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
