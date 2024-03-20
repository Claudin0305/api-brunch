package com.brunch.api.service.interfaces;

import com.brunch.api.entity.HistoriquePaiementRepas;

import java.util.List;

public interface HistoriquePaiementRepasService {
    List<HistoriquePaiementRepas> getAllHistoriquePaiementRepas();
    HistoriquePaiementRepas getHistoriquePaiementRepasById(Long historiquePaiementRepasId);
    HistoriquePaiementRepas createHistoriquePaiementRepas(HistoriquePaiementRepas historiquePaiement);
    HistoriquePaiementRepas updateHistoriquePaiementRepas(Long historiquePaiementRepasId, HistoriquePaiementRepas historiquePaiementRepas);
    void deletePaiementRepas(Long historiquePaiementRepasId);
}
