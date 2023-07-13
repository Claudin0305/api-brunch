package com.brunch.api.validators.classes;

import com.brunch.api.entity.Departement;
import com.brunch.api.repository.DepartementRepository;
import com.brunch.api.validators.interfaces.UniqueLibelleDept;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UniqueLibelleDeptValidator implements ConstraintValidator<UniqueLibelleDept, Departement> {
    @Autowired
    private DepartementRepository repository; // assuming you have a repository for MyEntity

    @Override
    public void initialize(UniqueLibelleDept constraintAnnotation) {}

    @Override
    public boolean isValid(Departement entity, ConstraintValidatorContext context) {
        if (entity == null) {
            return true;
        }

        List<Departement> existingEntities = (List<Departement>) repository.findByLibelleAndPays(entity.getLibelle(), entity.getPays());
        if (existingEntities.isEmpty()) {
            return true;
        } else if (existingEntities.size() == 1 && existingEntities.get(0).getId().equals(entity.getId())) {
            // if this is an update operation and the entity being validated is the same as the existing one, it's valid
            return true;
        } else {
            // otherwise, it's invalid
            return false;
        }
    }
}
