package com.brunch.api.service.interfaces;



import com.brunch.api.entity.Civilite;

import java.util.List;

public interface CiviliteService {
    List<Civilite> getAllCivilites();
    Civilite getCiviliteById(Long id_civilte);
    Civilite createCivilite(Civilite civilite);
    Civilite updateCivilite(Long id_civilite, Civilite updatedCivilite);
    void deleteCivilite(Long id_civilite);
}
