package com.brunch.api.service.interfaces;




import com.brunch.api.entity.Departement;

import java.util.List;

public interface DepartementService {
    List<Departement> getAllDepartements();
    Departement getDepartementById(Long id);
    Departement createDepartement(Departement departement);
    Departement updateDepartement(Long id, Departement updatedDepartement);
    void deleteDepartement(Long id);
}
