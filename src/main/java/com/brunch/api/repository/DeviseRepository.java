package com.brunch.api.repository;


import com.brunch.api.entity.Devise;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviseRepository extends JpaRepository<Devise, Long> {
    boolean existsByDevise(String devise);
    List<Devise> findAll(Sort sort);
    Optional<Devise> findByDevise(String devise);
}
