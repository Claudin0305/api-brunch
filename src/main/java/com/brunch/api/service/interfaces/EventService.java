package com.brunch.api.service.interfaces;




import com.brunch.api.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    Event getEventById(Long id_event);
    Event createEvent(Event event);
    Event updateEvent(Long id_vent, Event eventUpdate);
    void deleteEvent(Long id_event);
}
