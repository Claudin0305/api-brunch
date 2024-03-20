package com.brunch.api.service.interfaces;


import com.brunch.api.entity.PaiementRepas;

import java.util.List;

public interface PaiementRepasService {
    List<PaiementRepas> getAllPaiementRepas();
    PaiementRepas getPaiementRepasById(Long paiementRepasId);
    PaiementRepas createPaiementRepas(PaiementRepas paiementRepas);
    PaiementRepas updatePaiementRepas(Long paiementRepasId, PaiementRepas paiementRepas);
    void deletePaiementRepas(Long paiementRepasId);
}
