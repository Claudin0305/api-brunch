package com.brunch.api.repository;

import com.brunch.api.entity.PaiementRepas;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaiementRepasRepository extends JpaRepository<PaiementRepas, Long> {
    List<PaiementRepas> findAll(Sort sort);
}
