package com.brunch.api.controller;


import com.brunch.api.entity.DonObjectifPromesse;
import com.brunch.api.service.classes.DonObjectifPromesseService;
import com.brunch.api.utils.DonObjectifRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@ControllerAdvice
@RequestMapping("/api/don-objectif-promesse")
public class DonObjectifPromesseController {
    @Autowired
    private DonObjectifPromesseService donObjectifPromesseService;

    @GetMapping
    public ResponseEntity<List<DonObjectifPromesse>> getAll() {
        return ResponseEntity.ok(donObjectifPromesseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        DonObjectifPromesse donObjectifPromesse = donObjectifPromesseService.findOne(id);
        if (donObjectifPromesse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(donObjectifPromesse);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid DonObjectifPromesse donObjectifPromesse) {
        return ResponseEntity.status(HttpStatus.CREATED).body(donObjectifPromesseService.create(donObjectifPromesse));
    }

    @PostMapping("/ajouter-ou-enlever")
    public ResponseEntity<?> addOrReplace(@RequestBody @Valid DonObjectifRequest donObjectifRequest) {
        DonObjectifPromesse donObjectifPromesse = donObjectifPromesseService.findOne(donObjectifRequest.getId());
        if (donObjectifPromesse == null) {
            return ResponseEntity.notFound().build();
        }
        switch (donObjectifRequest.getOperation()){
            case "addition":
                switch (donObjectifRequest.getOption()){
                    case "don":
                        donObjectifPromesse.setDon(donObjectifRequest.getDon() + donObjectifPromesse.getDon());
                        break;
                        case "promesse":
                            donObjectifPromesse.setPromesse(donObjectifRequest.getPromesse() + donObjectifPromesse.getPromesse());
                            break;
                    case "objectif":
                        donObjectifPromesse.setObjectif(donObjectifRequest.getObjectif() + donObjectifPromesse.getObjectif());
                        break;
                }
                break;
            case "soustraction":
                switch (donObjectifRequest.getOption()){
                    case "don":
                        if(donObjectifPromesse.getDon()-donObjectifRequest.getDon() < 0){
                            return ResponseEntity.badRequest().build();
                        }
                        donObjectifPromesse.setDon(  donObjectifPromesse.getDon()-donObjectifRequest.getDon());
                        break;
                    case "promesse":
                        if(donObjectifPromesse.getPromesse()-donObjectifRequest.getPromesse() < 0){
                            return ResponseEntity.badRequest().build();
                        }
                        donObjectifPromesse.setPromesse(donObjectifPromesse.getPromesse()-donObjectifRequest.getPromesse());
                        break;
                    case "objectif":
                        if(donObjectifPromesse.getObjectif()-donObjectifRequest.getObjectif() < 0){
                            return ResponseEntity.badRequest().build();
                        }
                        donObjectifPromesse.setObjectif(donObjectifPromesse.getObjectif()-donObjectifRequest.getObjectif() );
                        break;
                }

        }
        return ResponseEntity.ok(donObjectifPromesseService.update(donObjectifRequest.getId(), donObjectifPromesse));
    }
}
