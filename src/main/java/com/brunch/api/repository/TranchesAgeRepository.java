package com.brunch.api.repository;

import com.brunch.api.entity.TrancheAge;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranchesAgeRepository extends JpaRepository<TrancheAge, Long> {
    boolean existsByLibelle(String libelle);
    List<TrancheAge> findAll(Sort sort);
}
