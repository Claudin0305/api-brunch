package com.brunch.api.controller;

import com.brunch.api.entity.Statut;
import com.brunch.api.service.classes.StatutSeviceImplement;
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
@RequestMapping("/api/statuts")
public class StatutController {
    @Autowired
    private StatutSeviceImplement statutSeviceImplement;


    @GetMapping
    public List<Statut> getAllStatuts(){
        return statutSeviceImplement.getAllStatuts();
    }
    @GetMapping("/{id_statut}")
    public Statut getStatutById(@PathVariable Long id_statut){
        return statutSeviceImplement.getStatutById(id_statut);
    }
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Statut> createStatut(@Valid @RequestBody Statut statut){


        Statut createStatut = statutSeviceImplement.createStatut(statut);
        return ResponseEntity.status(HttpStatus.CREATED).body(createStatut);
    }

    @PutMapping("/{id_statut}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updateDevise(@PathVariable Long id_statut, @RequestBody Statut statut){
        Statut st = statutSeviceImplement.getByLibelle(statut.getLibelle());
        if(st.getId_statut() == id_statut){
            System.out.println(st.getLibelle());
            Statut updateStatut = statutSeviceImplement.updateStatut(id_statut, statut);
            return  ResponseEntity.ok(updateStatut);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Libelle existe");
        }

    }

    @DeleteMapping("/{id_statut}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteDevise(@PathVariable Long id_statut){
        statutSeviceImplement.deleteStatut(id_statut);
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