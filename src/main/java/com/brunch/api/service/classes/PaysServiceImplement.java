package com.brunch.api.service.classes;

import com.brunch.api.entity.Pays;
import com.brunch.api.repository.PaysRepository;
import com.brunch.api.service.interfaces.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaysServiceImplement implements PaysService {
    @Autowired
    private PaysRepository paysRepository;
    @Override
    public List<Pays> getAllPays() {
        Sort sort = Sort.by("createdAt").descending();
        return paysRepository.findAll(sort);
    }

    @Override
    public Pays getPaysById(Long id) {
        return paysRepository.findById(id).orElse(null);
    }

    @Override
    public Pays createPays(Pays pays) {
        return paysRepository.save(pays);
    }

    @Override
    public Pays updatePays(Long id, Pays updatedPays) {
        Pays pays = paysRepository.findById(id).orElse(null);
        if(pays == null){
            return null;
        }
        pays.setPays_abbreviation(updatedPays.getPays_abbreviation());
        pays.setLibelle(updatedPays.getLibelle());
        return paysRepository.save(pays);
    }

    @Override
    public void deletePays(Long id) {
        paysRepository.deleteById(id);
    }

    @Override
    public Pays findByLibelle(String libelle) {
        return paysRepository.findByLibelle(libelle);
    }


}
