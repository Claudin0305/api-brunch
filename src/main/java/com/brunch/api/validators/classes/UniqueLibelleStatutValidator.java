package com.brunch.api.validators.classes;

import com.brunch.api.repository.StatutRepository;
import com.brunch.api.validators.interfaces.UniqueLibelleStatut;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueLibelleStatutValidator implements ConstraintValidator<UniqueLibelleStatut, String> {
    @Autowired
    private StatutRepository statutRepository;

    public void initialize(UniqueLibelleStatut constraint) {
    }

    public boolean isValid(String libelle, ConstraintValidatorContext context) {
        if(statutRepository == null){
            return true;
        }
        return libelle != null && !statutRepository.existsByLibelle(libelle);
    }
}
