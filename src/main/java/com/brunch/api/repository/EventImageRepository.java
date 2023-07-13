package com.brunch.api.repository;

import com.brunch.api.entity.EventImage;
import com.brunch.api.entity.Pays;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventImageRepository extends JpaRepository<EventImage, Long> {
    EventImage findByName(String name);
    List<EventImage> findAll(Sort sort);
}
