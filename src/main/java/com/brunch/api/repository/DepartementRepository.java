package com.brunch.api.repository;



import com.brunch.api.entity.Departement;
import com.brunch.api.entity.Pays;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {
//    boolean existsByLibelleAndIdPays(String libelle, Long id_pays);
    Pays findByLibelleAndPays(String libelle,  Pays pays);

    List<Departement> findAll(Sort sort);

}
