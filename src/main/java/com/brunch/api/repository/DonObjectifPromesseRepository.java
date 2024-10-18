package com.brunch.api.repository;
import com.brunch.api.entity.DonObjectifPromesse;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonObjectifPromesseRepository extends JpaRepository<DonObjectifPromesse, Long> {
    List<DonObjectifPromesse> findAll(Sort sort);
}
