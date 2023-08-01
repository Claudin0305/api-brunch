package com.brunch.api.controller;

import com.brunch.api.entity.Devise;
import com.brunch.api.entity.Event;
import com.brunch.api.entity.Local;
import com.brunch.api.entity.Ville;
import com.brunch.api.service.classes.DeviseServiceImplement;
import com.brunch.api.service.classes.EventServiceImplement;
import com.brunch.api.service.classes.LocalServiceImplement;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@ControllerAdvice
@RequestMapping("/api/locaux")
public class LocalController {
    @Autowired
    private LocalServiceImplement localServiceImplement;
    @Autowired
    private EventServiceImplement eventServiceImplement;
    @Autowired
    private VilleServiceImplement villeServiceImplement;
    @Autowired
    private DeviseServiceImplement deviseServiceImplement;

    @GetMapping
    public ResponseEntity<List<Local>> locals(){
        return ResponseEntity.ok(localServiceImplement.getAllLocals());
    }
    @GetMapping("/{id_local}")
    public ResponseEntity<Local> getLocalById(@PathVariable Long id_local){
        return ResponseEntity.ok(localServiceImplement.getLocalById(id_local));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Local> createLocalBrunch(@Valid @ModelAttribute Local local, @RequestParam(name = "id_devise") Long id_devise, @RequestParam(name = "id_event") Long id_event, @RequestParam(name = "id_ville") Long id_ville){
        Devise devise = deviseServiceImplement.getDeviseById(id_devise);
        Event event = eventServiceImplement.getEventById(id_event);
        Ville ville = villeServiceImplement.getVilleById(id_ville);
        local.setLocal_devise(devise);
        local.setLocal_ville(ville);
        local.setLocal_evenement(event);
        Local createLocal = localServiceImplement.createLocalBrunch(local);
        return ResponseEntity.status(HttpStatus.CREATED).body(createLocal);

    }

    @PutMapping("/{id_local}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Local> updateLocalBrunch(@RequestParam(name = "id_devise") Long id_devise, @RequestParam(name = "id_event") Long id_event, @RequestParam(name = "id_ville") Long id_ville, @PathVariable Long id_local, @Valid @ModelAttribute Local local){
        Devise devise = deviseServiceImplement.getDeviseById(id_devise);
        Event event = eventServiceImplement.getEventById(id_event);
        Ville ville = villeServiceImplement.getVilleById(id_ville);
        local.setLocal_devise(devise);
        local.setLocal_ville(ville);
        local.setLocal_evenement(event);
        Local updatedLocal = localServiceImplement.updateLocal(id_local, local);
        return  ResponseEntity.ok(updatedLocal);
    }

    @DeleteMapping("/{id_local}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteLocalBrunch(@PathVariable Long id_local){
        localServiceImplement.deleteLocal(id_local);
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
