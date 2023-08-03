package com.brunch.api.service.classes;

import com.brunch.api.entity.Local;
import com.brunch.api.repository.LocalRepository;
import com.brunch.api.service.interfaces.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LocalServiceImplement implements LocalService {

    @Autowired
    private LocalRepository localRepository;
    @Override
    public List<Local> getAllLocals() {
        Sort sort = Sort.by("createdAt").descending();
        return localRepository.findAll(sort);
    }

    @Override
    public Local getLocalById(Long id_local) {
        return localRepository.findById(id_local).orElse(null);
    }

    @Override
    public Local createLocalBrunch(Local local) {
        return localRepository.save(local);
    }

    @Override
    public Local updateLocal(Long id_local, Local localUpdate) {
        Local local = getLocalById(id_local);

        if(local == null){
            return null;
        }

        local.setCapacite_table(localUpdate.getCapacite_table());
        local.setCapacite_totale(localUpdate.getCapacite_totale());
        local.setSeuil_alerte(localUpdate.getSeuil_alerte());
        local.setEmail_responsable(localUpdate.getEmail_responsable());
        local.setAdresse_no_rue(localUpdate.getAdresse_no_rue());
        local.setNb_reservation(localUpdate.getNb_reservation());
        local.setLibelle(localUpdate.getLibelle());
        local.setLocal_devise(localUpdate.getLocal_devise());
        local.setLocal_ville(localUpdate.getLocal_ville());
        local.setLocal_evenement(localUpdate.getLocal_evenement());
        local.setMontant_participation(localUpdate.getMontant_participation());

        return localRepository.save(local);
    }

    @Override
    public void deleteLocal(Long id_local) {
        localRepository.deleteById(id_local);
    }
}
