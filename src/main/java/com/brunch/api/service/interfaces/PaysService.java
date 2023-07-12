package com.brunch.api.service.interfaces;



import com.brunch.api.entity.Pays;

import java.util.List;

public interface PaysService {
    List<Pays> getAllPays();
    Pays getPaysById(Long id);
    Pays createPays(Pays pays);
    Pays updatePays(Long id, Pays updatedPays);
    void deletePays(Long id);
   Pays findByLibelle(String libelle);
}
