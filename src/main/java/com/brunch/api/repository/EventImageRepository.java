package com.brunch.api.repository;

import com.brunch.api.entity.EventImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EventImageRepository extends JpaRepository<EventImage, Long> {
    Optional<EventImage> findByName(String fileName);
    EventImage findEventImageByName(String fileName);
//    EventImage findEventImageByIdEventActive(Long id_event, boolean active);
    EventImage findByActive(boolean active);
}
