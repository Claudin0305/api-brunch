package com.brunch.api.repository;

import com.brunch.api.entity.HistoriquePaiementRepas;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HistoriquePaiementRepasRepository extends JpaRepository<HistoriquePaiementRepas, Long> {
    List<HistoriquePaiementRepas> findAll(Sort sort);
}
