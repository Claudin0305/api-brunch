package com.brunch.api.service.classes;

import com.brunch.api.entity.PaiementRepas;
import com.brunch.api.repository.PaiementRepasRepository;
import com.brunch.api.service.interfaces.PaiementRepasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaiementRepasServiceImpl implements PaiementRepasService {
    @Autowired
    private PaiementRepasRepository paiementRepasRepository;
    @Override
    public List<PaiementRepas> getAllPaiementRepas() {
        Sort sort = Sort.by("createdAt").descending();
        return paiementRepasRepository.findAll(sort);

    }

    @Override
    public PaiementRepas getPaiementRepasById(Long paiementRepasId) {
        return paiementRepasRepository.findById(paiementRepasId).orElse(null);
    }

    @Override
    public PaiementRepas createPaiementRepas(PaiementRepas paiementRepas) {
        return paiementRepasRepository.save(paiementRepas);
    }

    @Override
    public PaiementRepas updatePaiementRepas(Long paiementRepasId, PaiementRepas paiementRepas) {
        PaiementRepas p = getPaiementRepasById(paiementRepasId);
        if(p == null){
            return null;
        }
        p.setDevise(paiementRepas.getDevise());
        p.setDate_dernier_paiement(paiementRepas.getDate_dernier_paiement());
        p.setParticipant(paiementRepas.getParticipant());
        p.setMontant_du(paiementRepas.getMontant_du());
        p.setMontant_paye(paiementRepas.getMontant_paye());
        p.setStatut(paiementRepas.getStatut());
        p.setReste_a_payer(p.getReste_a_payer());
        p.setMode_paiement(paiementRepas.getMode_paiement());
        p.setDon(paiementRepas.getDon());
        return paiementRepasRepository.save(p);
    }

    @Override
    public void deletePaiementRepas(Long paiementRepasId) {
        paiementRepasRepository.deleteById(paiementRepasId);
    }
}
