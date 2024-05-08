package com.brunch.api.repository;

import com.brunch.api.entity.ImageData;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StorageImageRepository extends JpaRepository<ImageData, Long> {
//    Optional<ImageData> findByName(String name);
    List<ImageData> findAll(Sort sort);
    ImageData findByName(String name);
}
