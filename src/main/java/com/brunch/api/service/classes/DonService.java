package com.brunch.api.service.classes;


import com.brunch.api.entity.Don;
import com.brunch.api.repository.DonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DonService {
    @Autowired
    private DonRepository donRepository;

    public List<Don> getAllEvents() {
        Sort sort = Sort.by("createdAt").descending();
        return donRepository.findAll(sort);
    }

    public Don getDonById(Long id) {
        return donRepository.findById(id).orElse(null);
    }


    public Don createDon(Don don) {
        return donRepository.save(don);
    }


    public Don updateDon(Long id, Don updatedDon) {
       Don don = getDonById(id);

        if(don == null){
            return null;
        }
        don.setParticipant(updatedDon.getParticipant());
        don.setMontant(updatedDon.getMontant());
        don.setUpdatedAt(new Date());

        return donRepository.save(don);
    }


    public void deleteDon(Long id) {
        donRepository.deleteById(id);
    }
}
