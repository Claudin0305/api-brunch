package com.brunch.api.repository;

import com.brunch.api.entity.Local;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {
    List<Local> findAll(Sort sort);

}
