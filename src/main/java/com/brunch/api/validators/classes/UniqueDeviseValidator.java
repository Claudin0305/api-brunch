package com.brunch.api.validators.classes;

import com.brunch.api.repository.DeviseRepository;
import com.brunch.api.validators.interfaces.UniqueDevise;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueDeviseValidator implements ConstraintValidator<UniqueDevise, String> {
    @Autowired
    private DeviseRepository deviseRepository;

    public void initialize(UniqueDevise constraint) {
    }

    public boolean isValid(String devise, ConstraintValidatorContext context) {
        if(deviseRepository == null){
            return true;
        }
        return devise != null && !deviseRepository.existsByDevise(devise);
    }
}
