package com.brunch.api.repository;

import com.brunch.api.entity.Civilite;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiviliteRepository extends JpaRepository<Civilite, Long> {
    Civilite findByLibelle(String libelle);
    boolean existsByLibelle(String libelle);
    List<Civilite> findAll(Sort sort);
}
