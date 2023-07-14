package com.brunch.api.controller;

import com.brunch.api.entity.ApplicationUser;
import com.brunch.api.service.classes.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<ApplicationUser>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<ApplicationUser> getVilleById(@PathVariable Integer user_id){
        return ResponseEntity.ok().body(userService.getUserById(user_id).orElse(null));

    }
}
