package com.brunch.api.repository;

import com.brunch.api.entity.Participant;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findAll(Sort sort);
    boolean existsByUsername(String username);
    List<Participant>findByAuthorisationListe(Sort sort, boolean authorisation_liste);
}
