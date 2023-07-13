package com.brunch.api.service.interfaces;



import com.brunch.api.entity.Participant;

import java.util.List;

public interface ParticipantService {
    List<Participant> getAllParticipants();
    Participant getParticipantById(Long id_participant);
    Participant createParticipant(Participant participant);
    Participant updateParticipant(Long id_participant, Participant participantUpdate);
    void deleteParticipant(Long id_participant);

}
