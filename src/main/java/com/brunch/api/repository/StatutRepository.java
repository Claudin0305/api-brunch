package com.brunch.api.repository;

import com.brunch.api.entity.Statut;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatutRepository extends JpaRepository<Statut, Long> {
    boolean existsByLibelle(String libelle);
    Statut findByLibelle(String libelle);
    List<Statut> findAll(Sort sort);
}
