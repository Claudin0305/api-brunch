package com.brunch.api.service.classes;


import com.brunch.api.entity.Ville;
import com.brunch.api.repository.VilleRepository;
import com.brunch.api.service.interfaces.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleServiceImplement implements VilleService {
@Autowired
private VilleRepository villeRepository;
    @Override
    public List<Ville> getAllVilles() {
        Sort sort = Sort.by("createdAt").descending();
        return villeRepository.findAll(sort);
    }

    @Override
    public Ville getVilleById(Long id_ville) {
        return villeRepository.findById(id_ville).orElse(null);
    }

    @Override
    public Ville createVille(Ville ville) {
        return villeRepository.save(ville);
    }

    @Override
    public Ville updateVille(Long id_ville, Ville updateVille) {
        Ville ville = villeRepository.findById(id_ville).orElse(null);
        if(ville == null){
            return null;
        }
        ville.setLibelle(updateVille.getLibelle());
        ville.setDepartement(updateVille.getDepartement());

        return villeRepository.save(ville);
    }

    @Override
    public void deleteVille(Long id_ville) {
        villeRepository.deleteById(id_ville);
    }
}
