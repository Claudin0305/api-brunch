package com.brunch.api.validators.classes;

import com.brunch.api.repository.PaysRepository;
import com.brunch.api.validators.interfaces.UniqueLibelle;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueLibelleValidator implements ConstraintValidator<UniqueLibelle, String> {
    @Autowired
    private PaysRepository paysRepository;

    public void initialize(UniqueLibelle constraint) {
    }

    public boolean isValid(String libelle, ConstraintValidatorContext context) {
if(paysRepository == null){
    return true;
}
        return libelle != null && !paysRepository.existsByLibelle(libelle);
    }
}
