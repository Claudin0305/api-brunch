package com.brunch.api.service.classes;

import com.brunch.api.entity.Statut;
import com.brunch.api.repository.StatutRepository;
import com.brunch.api.service.interfaces.StatutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatutSeviceImplement implements StatutService {
    @Autowired
    private StatutRepository statutRepository;
    @Override
    public List<Statut> getAllStatuts() {
        Sort sort = Sort.by("createdAt").descending();
        return statutRepository.findAll(sort);
    }

    @Override
    public Statut getStatutById(Long id_statut) {
        return statutRepository.findById(id_statut).orElse(null);
    }

    @Override
    public Statut createStatut(Statut statut) {
        return statutRepository.save(statut);
    }

    @Override
    public Statut updateStatut(Long id_statut, Statut updateStatut) {
        Statut statut = statutRepository.findById(id_statut).orElse(null);
        if(statut == null){
            return null;
        }
        statut.setLibelle(updateStatut.getLibelle());
        return statutRepository.save(statut);
    }
    public Statut getByLibelle(String libelle){
        return statutRepository.findByLibelle(libelle);
    }

    @Override
    public void deleteStatut(Long id_statut) {
        statutRepository.deleteById(id_statut);
    }
}
