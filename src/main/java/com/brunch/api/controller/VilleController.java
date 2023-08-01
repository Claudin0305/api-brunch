package com.brunch.api.controller;


import com.brunch.api.entity.Departement;
import com.brunch.api.entity.Ville;
import com.brunch.api.service.classes.DepartementServiceImplement;
import com.brunch.api.service.classes.VilleServiceImplement;
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
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
@ControllerAdvice
@RequestMapping("/api/villes")
public class VilleController {
    @Autowired
    private VilleServiceImplement villeServiceImplement;
    @Autowired
    private DepartementServiceImplement departementServiceImplement;
    @GetMapping
    List<Ville> getAllVilles(){
        return villeServiceImplement.getAllVilles();
    }
    @GetMapping("/{id_ville}")
    Ville getVilleById(@PathVariable Long id_ville){
        return villeServiceImplement.getVilleById(id_ville);

    }

    @PostMapping()
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Ville> createVille(@Valid @ModelAttribute Ville ville, @RequestParam("id_departement") Long id_departement){

        Departement departement = departementServiceImplement.getDepartementById(id_departement);
        ville.setDepartement(departement);
        Ville createVille = villeServiceImplement.createVille(ville);
        return ResponseEntity.status(HttpStatus.CREATED).body(createVille);

    }
    @PostMapping("/from-client")
    public ResponseEntity<Ville> createVilleFromUser(@Valid @ModelAttribute Ville ville, @RequestParam("id_departement") Long id_departement){

        Departement departement = departementServiceImplement.getDepartementById(id_departement);
        ville.setDepartement(departement);
        Ville createVille = villeServiceImplement.createVille(ville);
        return ResponseEntity.status(HttpStatus.CREATED).body(createVille);

    }



    @PutMapping("/{id_ville}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Ville> updateVille(@RequestParam("id_departement") Long id_departement, @PathVariable Long id_ville, @Valid @ModelAttribute Ville ville){
        Departement departement = departementServiceImplement.getDepartementById(id_departement);
        ville.setDepartement(departement);
        Ville villeUpdate = villeServiceImplement.updateVille(id_ville, ville);
        return  ResponseEntity.ok(villeUpdate);
    }

    @DeleteMapping("/{id_ville}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteVille(@PathVariable Long id_ville){
        villeServiceImplement.deleteVille(id_ville);
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
