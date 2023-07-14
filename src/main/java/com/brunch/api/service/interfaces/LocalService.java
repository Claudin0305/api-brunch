package com.brunch.api.service.interfaces;


import com.brunch.api.entity.Local;

import java.util.List;

public interface LocalService {
    List<Local> getAllLocals();
    Local getLocalById(Long id_local);
    Local createLocalBrunch(Local local);
    Local updateLocal(Long id_local, Local localUpdate);
    void deleteLocal(Long id_local);
}
