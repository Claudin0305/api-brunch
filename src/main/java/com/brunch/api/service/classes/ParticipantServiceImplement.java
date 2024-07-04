package com.brunch.api.service.classes;


import com.brunch.api.entity.Participant;
import com.brunch.api.repository.ParticipantRepository;
import com.brunch.api.service.interfaces.ParticipantService;
import com.brunch.api.utils.FormatEvent;
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

    public List<Participant> findByAuthorisationListe() {
        Sort sort = Sort.by("createdAt").descending();
        return participantRepository.findByAuthorisationListe(sort, true);
    }

    @Override
    public Participant getParticipantById(Long id_participant) {
        return participantRepository.findById(id_participant).orElse(null);
    }

    public boolean existsByUsername(String username) {
        return participantRepository.existsByUsername(username);
    }

    @Override
    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    @Override
    public Participant updateParticipant(Long id_participant, Participant participantUpdate) {
        Participant participant = getParticipantById(id_participant);
        if (participant == null) {
            return null;
        }
        participant.setAffiliation(participantUpdate.getAffiliation());
        participant.setNom(participantUpdate.getNom());
        participant.setPrenom(participantUpdate.getPrenom());
        participant.setTelParticipant(participantUpdate.getTelParticipant());
        participant.setEmail(participantUpdate.getEmail());
        participant.setAbonnement_newsletter(participantUpdate.isAbonnement_newsletter());
        participant.setEmail_valide(participantUpdate.isEmail_valide());
        participant.setAuthorisationListe(participantUpdate.isAuthorisationListe());
        participant.setInscritPar(participantUpdate.getInscritPar());
        participant.setModePiement(participantUpdate.getModePiement());
        participant.setStatut_participant(participantUpdate.getStatut_participant());
        participant.setStatut_payment(participantUpdate.getStatut_payment());
        participant.setEmail_payeur(participantUpdate.getEmail_payeur());
        participant.setPayeur(participantUpdate.getPayeur());
        participant.setDatePaiement(participantUpdate.getDatePaiement());
        participant.setModePiement(participantUpdate.getModePiement());
        return participantRepository.save(participant);
    }

    public Optional<Participant> findByUsername(String username) {
        return participantRepository.findByUsername(username);
    }

    public Participant findByUsernameOrEmailOrPhone(String info) {
        return participantRepository.findByUsernameOrEmailOrTelParticipant(info, info, info);
    }

    @Override
    public void deleteParticipant(Long id_participant) {
        participantRepository.deleteById(id_participant);
    }

    public List<Participant> findByInscritPar(String id) {
        return participantRepository.findByInscritPar(id);
    }
    public List<Participant> findByModeParticipation(FormatEvent formatEvent){
        return participantRepository.findByModeParticipation(formatEvent);
    }
}
