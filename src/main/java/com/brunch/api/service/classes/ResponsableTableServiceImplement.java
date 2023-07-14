package com.brunch.api.service.classes;


import com.brunch.api.entity.ResponsableTable;
import com.brunch.api.repository.ResponsableTableRepository;
import com.brunch.api.service.interfaces.ResponsableTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResponsableTableServiceImplement implements ResponsableTableService {
    @Autowired
    private ResponsableTableRepository responsableTableRepository;
    @Override
    public List<ResponsableTable> getAllResponsableTables() {
        Sort sort = Sort.by("createdAt").descending();
        return responsableTableRepository.findAll(sort);
    }

    @Override
    public ResponsableTable getResponsableTableById(Long id_responsable) {
        return responsableTableRepository.findById(id_responsable).orElse(null);
    }

    @Override
    public ResponsableTable createResponsableTable(ResponsableTable responsableTable) {
        return responsableTableRepository.save(responsableTable);
    }

    @Override
    public ResponsableTable updateResponsableTable(Long id_responsable, ResponsableTable responsableTableUpdate) {
        ResponsableTable responsableTable = getResponsableTableById(id_responsable);

        if(responsableTable == null){
            return null;
        }
        responsableTable.setEmailResponsable(responsableTableUpdate.getEmailResponsable());
        responsableTable.setNomResponsable(responsableTableUpdate.getNomResponsable());
        responsableTable.setPrenomResponsable(responsableTableUpdate.getPrenomResponsable());
        responsableTable.setTelResponsable(responsableTableUpdate.getTelResponsable());
        responsableTable.setObjectifs(responsableTableUpdate.getObjectifs());
        responsableTable.setRealisation(responsableTableUpdate.getRealisation());
        return responsableTableRepository.save(responsableTable);
    }

    @Override
    public void deleteResponsableTable(Long id_responsable) {
        responsableTableRepository.deleteById(id_responsable);
    }
}
