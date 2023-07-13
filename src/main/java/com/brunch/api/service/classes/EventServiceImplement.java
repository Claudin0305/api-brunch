package com.brunch.api.service.classes;

import com.brunch.api.entity.Event;
import com.brunch.api.repository.EventRepository;
import com.brunch.api.service.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventServiceImplement implements EventService {
    @Autowired
    private EventRepository eventRepository;
    @Override
    public List<Event> getAllEvents() {
        Sort sort = Sort.by("createdAt").descending();
        return eventRepository.findAll(sort);
    }

    @Override
    public Event getEventById(Long id_event) {
        return eventRepository.findById(id_event).orElse(null);
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id_vent, Event eventUpdate) {
        Event event = getEventById(id_vent);

        if(event == null){
            return null;
        }
        event.setFormat_event(eventUpdate.getFormat_event());
        event.setCible_participation(eventUpdate.getCible_participation());
        event.setAdr_email_event(eventUpdate.getAdr_email_event());
        event.setDate_debut(eventUpdate.getDate_debut());
        event.setDate_fin(eventUpdate.getDate_fin());
        event.setHeure_debut(eventUpdate.getHeure_debut());
        event.setHeure_fin(eventUpdate.getHeure_fin());
        event.setUrl(eventUpdate.getUrl());
        event.setText_descriptif(eventUpdate.getText_descriptif());
        event.setEventType(eventUpdate.getEventType());
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id_event) {
        eventRepository.deleteById(id_event);
    }
}
