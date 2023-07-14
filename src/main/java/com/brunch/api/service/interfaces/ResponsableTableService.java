package com.brunch.api.service.interfaces;


import com.brunch.api.entity.ResponsableTable;

import java.util.List;

public interface ResponsableTableService {
    List<ResponsableTable> getAllResponsableTables();
    ResponsableTable getResponsableTableById(Long id_responsable);
    ResponsableTable createResponsableTable(ResponsableTable responsableTable);
    ResponsableTable updateResponsableTable(Long id_responsable, ResponsableTable responsableTableUpdate);
    void deleteResponsableTable(Long id_responsable);
}
