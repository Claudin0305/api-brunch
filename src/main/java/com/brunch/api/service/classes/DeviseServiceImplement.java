package com.brunch.api.service.classes;


import com.brunch.api.entity.Devise;
import com.brunch.api.repository.DeviseRepository;
import com.brunch.api.service.interfaces.DeviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviseServiceImplement implements DeviseService {
    @Autowired
    private DeviseRepository deviseRepository;
    @Override
    public List<Devise> getAllDevises() {
        Sort sort = Sort.by("createdAt").descending();
        return deviseRepository.findAll(sort);
    }

    @Override
    public Devise getDeviseById(Long id_devise) {
        return deviseRepository.findById(id_devise).orElse(null);
    }

    @Override
    public Devise createDevise(Devise devise) {
        return deviseRepository.save(devise);
    }

    @Override
    public Devise updateDevise(Long id_devise, Devise updatedDevise) {
        Devise devise = deviseRepository.findById(id_devise).orElse(null);
        if(devise == null){
            return null;
        }

        devise.setDevise(updatedDevise.getDevise());
        devise.setCode_devise(updatedDevise.getCode_devise());
        return deviseRepository.save(devise);
    }

    @Override
    public void deleteDevise(Long id_civilite) {
deviseRepository.deleteById(id_civilite);
    }
}
