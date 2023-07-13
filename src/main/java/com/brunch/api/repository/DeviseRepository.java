package com.brunch.api.repository;


import com.brunch.api.entity.Devise;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviseRepository extends JpaRepository<Devise, Long> {
    boolean existsByDevise(String devise);
    List<Devise> findAll(Sort sort);
}
