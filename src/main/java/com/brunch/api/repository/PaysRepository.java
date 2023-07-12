package com.brunch.api.repository;

import com.brunch.api.entity.Pays;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long> {
    Pays findByLibelle(String libelle);
    boolean existsByLibelle(String libelle);
    List<Pays> findAll(Sort sort);
}
