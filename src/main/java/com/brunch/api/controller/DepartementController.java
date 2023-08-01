package com.brunch.api.controller;



import com.brunch.api.entity.Departement;
import com.brunch.api.entity.Pays;
import com.brunch.api.service.classes.DepartementServiceImplement;
import com.brunch.api.service.classes.PaysServiceImplement;
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
@RequestMapping("/api/departements")
public class DepartementController {
    @Autowired
    private DepartementServiceImplement departementServiceImplement;
    @Autowired
    private PaysServiceImplement paysServiceImplement;
    @GetMapping
    public List<Departement> getAllDepartements(){
        return departementServiceImplement.getAllDepartements();
    }
    @GetMapping("/{id_departement}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Departement getDepartementById(@PathVariable Long id_departement){
        return departementServiceImplement.getDepartementById(id_departement);
    }
    @PostMapping()
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Departement> createDepartement(@Valid @ModelAttribute Departement departement, @RequestParam("id_pays") Long id_pays){
        Pays pays = paysServiceImplement.getPaysById(id_pays);
        departement.setPays(pays);
//        return ResponseEntity.status(HttpStatus.CREATED).body(departement);
        Departement createDepartement = departementServiceImplement.createDepartement(departement);
        return ResponseEntity.status(HttpStatus.CREATED).body(createDepartement);

    }

    @PutMapping("/{id_departement}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Departement> updateDepartement(@RequestParam("id_pays") Long id_pays, @PathVariable Long id_departement, @ModelAttribute Departement departement){
        Pays pays = paysServiceImplement.getPaysById(id_pays);
        departement.setPays(pays);
        Departement updatedDepartement = departementServiceImplement.updateDepartement(id_departement, departement);
        return  ResponseEntity.ok(updatedDepartement);
    }

    @DeleteMapping("/{id_departement}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteDepartement(@PathVariable Long id_departement){
        departementServiceImplement.deleteDepartement(id_departement);
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
