package com.brunch.api.service.interfaces;



import com.brunch.api.entity.Ville;

import java.util.List;

public interface VilleService {
    List<Ville> getAllVilles();
    Ville getVilleById(Long id_ville);
    Ville createVille(Ville ville);
    Ville updateVille(Long id_ville, Ville updateVille);
    void deleteVille(Long id_ville);
}
