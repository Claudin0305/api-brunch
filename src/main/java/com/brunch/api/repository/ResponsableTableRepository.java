package com.brunch.api.repository;

import com.brunch.api.entity.ResponsableTable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ResponsableTableRepository extends JpaRepository<ResponsableTable, Long> {
    List<ResponsableTable> findAll(Sort sort);
}
