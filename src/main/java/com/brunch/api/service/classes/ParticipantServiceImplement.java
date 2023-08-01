package com.brunch.api.service.classes;


import com.brunch.api.entity.Participant;
import com.brunch.api.repository.ParticipantRepository;
import com.brunch.api.service.interfaces.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantServiceImplement implements ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;
    @Override
    public List<Participant> getAllParticipants() {
        Sort sort = Sort.by("createdAt").descending();
        return participantRepository.findAll(sort);
    }
public List<Participant> findByAuthorisationListe(){
    Sort sort = Sort.by("createdAt").descending();
    return participantRepository.findByAuthorisationListe(sort, true);
}
    @Override
    public Participant getParticipantById(Long id_participant) {
        return participantRepository.findById(id_participant).orElse(null);
    }
public boolean existsByUsername(String username){
        return participantRepository.existsByUsername(username);
}
    @Override
    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    @Override
    public Participant updateParticipant(Long id_participant, Participant participantUpdate) {
        Participant participant = getParticipantById(id_participant);
        if(participant == null){
            return null;
        }
        participant.setAffiliation(participantUpdate.getAffiliation());
        participant.setNom(participantUpdate.getNom());
        participant.setPrenom(participantUpdate.getPrenom());
        participant.setTel_participant(participantUpdate.getTel_participant());
        participant.setEmail(participantUpdate.getEmail());
        participant.setAbonnement_newsletter(participantUpdate.isAbonnement_newsletter());
        participant.setEmail_valide(participantUpdate.isEmail_valide());
        participant.setAuthorisationListe(participantUpdate.isAuthorisationListe());
        participant.setInscrit_par(participantUpdate.getInscrit_par());
        participant.setModePaiement(participantUpdate.getModePaiement());
        return participantRepository.save(participant);
    }

    public Optional<Participant> findByUsername(String  username){
        return participantRepository.findByUsername(username);
    }

    @Override
    public void deleteParticipant(Long id_participant) {
        participantRepository.deleteById(id_participant);
    }
}
