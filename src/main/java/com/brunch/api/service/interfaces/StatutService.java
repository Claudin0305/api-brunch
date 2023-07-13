package com.brunch.api.service.interfaces;



import com.brunch.api.entity.Statut;

import java.util.List;

public interface StatutService {
    List<Statut> getAllStatuts();
    Statut getStatutById(Long id_statut);
    Statut createStatut(Statut statut);
    Statut updateStatut(Long id_statut, Statut updateStatut);
    void deleteStatut(Long id_statut);
}
