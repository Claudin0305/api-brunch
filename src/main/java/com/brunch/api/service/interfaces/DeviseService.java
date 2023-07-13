package com.brunch.api.service.interfaces;



import com.brunch.api.entity.Devise;

import java.util.List;

public interface DeviseService {
    List<Devise> getAllDevises();
    Devise getDeviseById(Long id_devise);
    Devise createDevise(Devise devise);
    Devise updateDevise(Long id_devise, Devise updatedDevise);
    void deleteDevise(Long id_devise);
}
