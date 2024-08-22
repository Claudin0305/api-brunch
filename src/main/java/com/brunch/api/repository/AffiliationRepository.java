package com.brunch.api.repository;

import com.brunch.api.entity.Affiliation;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AffiliationRepository extends JpaRepository<Affiliation, Long> {
    List<Affiliation> findAll(Sort sort);
    List<Affiliation>findAllByValidate(boolean validate, Sort sort);
}
