package com.brunch.api.service.classes;


import com.brunch.api.entity.TrancheAge;
import com.brunch.api.repository.TranchesAgeRepository;
import com.brunch.api.service.interfaces.TranchesAgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranchesAgeServiceImplement implements TranchesAgeService {
    @Autowired
    private TranchesAgeRepository tranchesAgeRepository;
    @Override
    public List<TrancheAge> getAllTranchesAges() {
        Sort sort = Sort.by("createdAt").descending();
        return tranchesAgeRepository.findAll(sort);
    }

    @Override
    public TrancheAge getTrancheAgeById(Long id_tranche_age) {
        return tranchesAgeRepository.findById(id_tranche_age).orElse(null);
    }

    @Override
    public TrancheAge createTranchesAge(TrancheAge trancheAge) {
        return tranchesAgeRepository.save(trancheAge);
    }

    @Override
    public TrancheAge updateTranchesAge(Long id_tranche_age, TrancheAge updatedTrancheAge) {
        TrancheAge trancheAge = getTrancheAgeById(id_tranche_age);
        if(trancheAge == null){
            return null;
        }
        trancheAge.setLibelle(updatedTrancheAge.getLibelle());
        return tranchesAgeRepository.save(trancheAge);
    }

    @Override
    public void deleteTranchesAge(Long id_tranche_age) {
        tranchesAgeRepository.deleteById(id_tranche_age);
    }
}
