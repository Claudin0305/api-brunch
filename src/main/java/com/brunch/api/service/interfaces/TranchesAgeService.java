package com.brunch.api.service.interfaces;




import com.brunch.api.entity.TrancheAge;

import java.util.List;

public interface TranchesAgeService {
    List<TrancheAge> getAllTranchesAges();
    TrancheAge getTrancheAgeById(Long id_tranche_age);
    TrancheAge createTranchesAge(TrancheAge trancheAge);
    TrancheAge updateTranchesAge(Long id_tranche_age, TrancheAge updatedTrancheAge);
    void deleteTranchesAge(Long id_tranche_age);
}
