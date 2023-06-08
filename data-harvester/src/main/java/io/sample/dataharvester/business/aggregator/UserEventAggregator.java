package io.sample.dataharvester.business.aggregator;

import io.sample.dataharvester.business.service.EventDetailService;
import io.sample.dataharvester.business.service.UserService;
import io.sample.dataharvester.model.Event;
import io.sample.dataharvester.model.UserEventInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class UserEventAggregator {

    private final UserService userService;
    private final EventDetailService eventDetailService;
    private final Map<Integer, UserEventInfo> events = new ConcurrentHashMap<>();

    public void aggregateEvent(Event event) {
        events.computeIfAbsent(event.getUser().getId(), id -> {
            UserEventInfo newAggregatedEvent = new UserEventInfo();
            newAggregatedEvent.setUserId(id);
            newAggregatedEvent.setName(event.getUser().getFirstName() + " " + event.getUser().getLastName());
            newAggregatedEvent.setAge(userService.calculateAge(event.getUser().getDateOfBirth()));
            return newAggregatedEvent;
        }).getEvents().add(eventDetailService.createEventDetail(event));
    }

    public Collection<UserEventInfo> getAggregatedEvents() {
        return events.values();
    }
}