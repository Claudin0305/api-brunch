package com.brunch.api.repository;

import com.brunch.api.entity.Ville;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {
    List<Ville> findAll(Sort sort);
}
