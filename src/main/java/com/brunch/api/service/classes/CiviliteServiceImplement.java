package com.brunch.api.service.classes;


import com.brunch.api.entity.Civilite;
import com.brunch.api.repository.CiviliteRepository;
import com.brunch.api.service.interfaces.CiviliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiviliteServiceImplement implements CiviliteService {
    @Autowired
    private CiviliteRepository civiliteRepository;
    @Override
    public List<Civilite> getAllCivilites() {
        Sort sort = Sort.by("createdAt").descending();

        return civiliteRepository.findAll(sort);
    }

    @Override
    public Civilite getCiviliteById(Long id_civilite) {
        return civiliteRepository.findById(id_civilite).orElse(null);
    }

    @Override
    public Civilite createCivilite(Civilite civilite) {
        return civiliteRepository.save(civilite);
    }

    @Override
    public Civilite updateCivilite(Long id_civilite, Civilite updatedCivilite) {
        Civilite civilite = civiliteRepository.findById(id_civilite).orElse(null);
        if(civilite == null){
            return null;
        }

        civilite.setLibelle(updatedCivilite.getLibelle());
        return civiliteRepository.save(civilite);
    }

    @Override
    public void deleteCivilite(Long id) {
        civiliteRepository.deleteById(id);
    }


}
