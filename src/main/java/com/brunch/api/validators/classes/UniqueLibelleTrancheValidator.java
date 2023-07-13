package com.brunch.api.validators.classes;

import com.brunch.api.repository.TranchesAgeRepository;
import com.brunch.api.validators.interfaces.UniqueLibelleTranche;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueLibelleTrancheValidator implements ConstraintValidator<UniqueLibelleTranche, String> {
    @Autowired
    private TranchesAgeRepository tranchesAgeRepository;

    public void initialize(UniqueLibelleTranche constraint) {
    }

    public boolean isValid(String libelle, ConstraintValidatorContext context) {
        if(tranchesAgeRepository == null){
            return true;
        }
        return libelle != null && !tranchesAgeRepository.existsByLibelle(libelle);
    }
}
