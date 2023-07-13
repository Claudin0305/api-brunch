package com.brunch.api.controller;


import com.brunch.api.entity.Civilite;
import com.brunch.api.entity.Event;
import com.brunch.api.entity.ResponsableTable;
import com.brunch.api.service.classes.CiviliteServiceImplement;
import com.brunch.api.service.classes.EventServiceImplement;
import com.brunch.api.service.classes.ResponsableTableServiceImplement;
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
@RequestMapping("/responsable-tables")
public class ResponsableTableController {
    @Autowired
    private ResponsableTableServiceImplement responsableTableServiceImplement;
    @Autowired
    private CiviliteServiceImplement civiliteServiceImplement;
    @Autowired
    private EventServiceImplement eventServiceImplement;
    @GetMapping
    public List<ResponsableTable> getAllResponsableTables(){
        return responsableTableServiceImplement.getAllResponsableTables();
    }
    @GetMapping("/{id_responsable}")
    public ResponsableTable getResponsableTableById(@PathVariable Long id_departement){
        return responsableTableServiceImplement.getResponsableTableById(id_departement);
    }
    @PostMapping("/{id_civilite}/{id_event}")
    public ResponseEntity<ResponsableTable> createResponsableTable(@Valid @RequestBody ResponsableTable responsableTable, @PathVariable Long id_civilite, @PathVariable Long id_event){
        Event event = eventServiceImplement.getEventById(id_event);
        Civilite civilite = civiliteServiceImplement.getCiviliteById(id_civilite);
        responsableTable.setEvent_(event);
        responsableTable.setCivilite(civilite);
        ResponsableTable createResponsableTable = responsableTableServiceImplement.createResponsableTable(responsableTable);
        return ResponseEntity.status(HttpStatus.CREATED).body(createResponsableTable);

    }

    @PutMapping("/{id_civilite}/{id_event}/{id_responsable}")
    public ResponseEntity<ResponsableTable> updateResponsableTable(@PathVariable Long id_civilite, @PathVariable Long id_event, @PathVariable Long id_responsable, @RequestBody ResponsableTable responsableTable){
        Event event = eventServiceImplement.getEventById(id_event);
        Civilite civilite = civiliteServiceImplement.getCiviliteById(id_civilite);
        responsableTable.setEvent_(event);
        responsableTable.setCivilite(civilite);
        ResponsableTable updatedResponsableTable = responsableTableServiceImplement.updateResponsableTable(id_responsable, responsableTable);
        return  ResponseEntity.ok(updatedResponsableTable);
    }

    @DeleteMapping("/{id_responsable}")
    public ResponseEntity<?> deleteResponsableTable(@PathVariable Long id_responsable){
        responsableTableServiceImplement.deleteResponsableTable(id_responsable);
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