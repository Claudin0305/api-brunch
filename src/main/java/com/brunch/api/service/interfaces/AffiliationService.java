package com.brunch.api.service.interfaces;

import com.brunch.api.entity.Affiliation;

import java.util.List;

public interface AffiliationService {
    List<Affiliation> getAllAffiliations();
    Affiliation getAffiliationById(Long affiliationId);
    Affiliation createAffiliation(Affiliation affiliation);
    Affiliation updateAffiliation(Long affiliationId, Affiliation updateAffiliation);
    void deleteAffiliation(Long affiliationId);
}
