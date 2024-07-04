package com.brunch.api.service.classes;

import com.brunch.api.entity.HistoriquePaiementRepas;
import com.brunch.api.repository.HistoriquePaiementRepasRepository;
import com.brunch.api.service.interfaces.HistoriquePaiementRepasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HistoriquePaiementServiceImpl implements HistoriquePaiementRepasService {
    @Autowired
    private HistoriquePaiementRepasRepository historiquePaiementRepasRepository;
    @Override
    public List<HistoriquePaiementRepas> getAllHistoriquePaiementRepas() {
        Sort sort = Sort.by("createdAt").descending();
        return historiquePaiementRepasRepository.findAll(sort);
    }

    @Override
    public HistoriquePaiementRepas getHistoriquePaiementRepasById(Long historiquePaiementRepasId) {
        return historiquePaiementRepasRepository.findById(historiquePaiementRepasId).orElse(null);
    }

    @Override
    public HistoriquePaiementRepas createHistoriquePaiementRepas(HistoriquePaiementRepas historiquePaiementRepas) {
        return historiquePaiementRepasRepository.save(historiquePaiementRepas);
    }

    @Override
    public HistoriquePaiementRepas updateHistoriquePaiementRepas(Long historiquePaiementRepasId, HistoriquePaiementRepas historiquePaiementRepas) {
        HistoriquePaiementRepas h = getHistoriquePaiementRepasById(historiquePaiementRepasId);
        if(h == null){
            return null;
        }
        h.setDate_paiement(historiquePaiementRepas.getDate_paiement());
        h.setDeviseHistoriquePaiement(historiquePaiementRepas.getDeviseHistoriquePaiement());
        h.setParticipant(historiquePaiementRepas.getParticipant());
        h.setMontant_paye(historiquePaiementRepas.getMontant_paye());

        return historiquePaiementRepasRepository.save(h);
    }

    @Override
    public void deletePaiementRepas(Long historiquePaiementRepasId) {
        historiquePaiementRepasRepository.deleteById(historiquePaiementRepasId);
    }
}
