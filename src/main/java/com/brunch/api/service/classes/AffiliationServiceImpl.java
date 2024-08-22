package com.brunch.api.service.classes;

import com.brunch.api.entity.Affiliation;
import com.brunch.api.repository.AffiliationRepository;
import com.brunch.api.service.interfaces.AffiliationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AffiliationServiceImpl implements AffiliationService {
    @Autowired
    private AffiliationRepository affiliationRepository;
    @Override
    public List<Affiliation> getAllAffiliations() {
//        Sort sort = Sort.by("createdAt").descending();
        return affiliationRepository.findAll(Sort.by("createdAt").descending());
    }

    @Override
    public Affiliation getAffiliationById(Long affiliationId) {
        return affiliationRepository.findById(affiliationId).orElse(null);
    }

    @Override
    public Affiliation createAffiliation(Affiliation affiliation) {
        return affiliationRepository.save(affiliation);
    }

    @Override
    public Affiliation updateAffiliation(Long affiliationId, Affiliation updateAffiliation) {
        Affiliation affiliation = getAffiliationById(affiliationId);
        if(affiliation == null){
            return null;
        }

        affiliation.setNom_affiliation(updateAffiliation.getNom_affiliation());
        affiliation.setValidate(updateAffiliation.getValidate());
        return affiliationRepository.save(affiliation);
    }
    public List<Affiliation> findValidate(){
        return affiliationRepository.findAllByValidate(true,Sort.by("createdAt").descending());
    }

    @Override
    public void deleteAffiliation(Long affiliationId) {
        affiliationRepository.deleteById(affiliationId);
    }
}
