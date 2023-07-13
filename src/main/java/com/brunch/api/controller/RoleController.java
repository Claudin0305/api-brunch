package com.brunch.api.controller;

import com.brunch.api.entity.Role;
import com.brunch.api.repository.RoleRepository;
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
@RequestMapping("/roles")
public class RoleController{
    @Autowired
    private RoleRepository roleRepository;


    @GetMapping
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }
    @GetMapping("/{role_id}")
    public Role getRoleById(@PathVariable Integer role_id){
        return roleRepository.getById(role_id);
    }
    @PostMapping
    public ResponseEntity<Role> createPays(@Valid @RequestBody Role role){


        Role createRole = roleRepository.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(createRole);
    }

    @PutMapping(value = "/{role_id}")
    public ResponseEntity<Role> updateRole(@PathVariable Integer role_id, @RequestBody Role role){
        Role roleUpdate = roleRepository.findById(role_id).orElse(null);
        if(roleUpdate == null){
            return null;
        }
        roleUpdate.setAuthority(role.getAuthority());
        return  ResponseEntity.ok(roleUpdate);
    }

    @DeleteMapping("/{role_id}")
    public ResponseEntity<?> deleteRole(@PathVariable Integer role_id){
        roleRepository.deleteById(role_id);
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
