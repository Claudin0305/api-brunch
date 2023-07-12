package com.brunch.api.controller;

import com.brunch.api.entity.Pays;
import com.brunch.api.service.classes.PaysServiceImplement;
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
@RequestMapping("/pays")
public class PaysController {
    @Autowired
    private PaysServiceImplement paysService;


    @GetMapping
    public List<Pays> getAllPays(){
        return paysService.getAllPays();
    }
    @GetMapping("/{id_pays}")
    public Pays getPaysById(@PathVariable Long id_pays){
        return paysService.getPaysById(id_pays);
    }
    @PostMapping
    public ResponseEntity<Pays> createPays(@Valid @RequestBody Pays pays){


        Pays createPays = paysService.createPays(pays);
        return ResponseEntity.status(HttpStatus.CREATED).body(createPays);
    }

    @PutMapping(value = "/{id_pays}")
    public ResponseEntity<Pays> updatePays(@PathVariable Long id_pays, @RequestBody Pays pays){
        Pays updatePays = paysService.updatePays(id_pays, pays);
        return  ResponseEntity.ok(updatePays);
    }

    @DeleteMapping("/{id_pays}")
    public ResponseEntity<?> deletePays(@PathVariable Long id_pays){
        paysService.deletePays(id_pays);
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
