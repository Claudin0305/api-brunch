package com.brunch.api.service.classes;


import com.brunch.api.entity.Departement;
import com.brunch.api.repository.DepartementRepository;
import com.brunch.api.service.interfaces.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartementServiceImplement implements DepartementService {
    @Autowired
    private DepartementRepository departementRepository;
    @Override
    public List<Departement> getAllDepartements() {
        Sort sort = Sort.by("createdAt").descending();
        return departementRepository.findAll(sort);
    }

    @Override
    public Departement getDepartementById(Long id) {
        return departementRepository.findById(id).orElse(null);
    }

    @Override
    public Departement createDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    @Override
    public Departement updateDepartement(Long id, Departement updatedDepartement) {
        Departement departement = departementRepository.findById(id).orElse(null);
        if(departement == null){
            return null;
        }
        departement.setDept_abbreviation(updatedDepartement.getDept_abbreviation());
        departement.setLibelle(updatedDepartement.getLibelle());
        return departementRepository.save(departement);
    }

    @Override
    public void deleteDepartement(Long id) {
departementRepository.deleteById(id);
    }
}
