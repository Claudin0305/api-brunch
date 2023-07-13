package com.brunch.api.validators.classes;

import com.brunch.api.repository.CiviliteRepository;
import com.brunch.api.validators.interfaces.UniqueLibelleCivilite;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueLibelleCiviliteValidator implements ConstraintValidator<UniqueLibelleCivilite, String> {
    @Autowired
    private CiviliteRepository civiliteRepository;

    public void initialize(UniqueLibelleCivilite constraint) {
    }

    public boolean isValid(String libelle, ConstraintValidatorContext context) {
        if(civiliteRepository == null){
            return true;
        }
        return libelle != null && !civiliteRepository.existsByLibelle(libelle);
    }
}