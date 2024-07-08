package com.brunch.api.repository;

import com.brunch.api.entity.Don;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface DonRepository extends JpaRepository<Don, Long> {
    List<Don> findAllBy(Sort sort);
}
