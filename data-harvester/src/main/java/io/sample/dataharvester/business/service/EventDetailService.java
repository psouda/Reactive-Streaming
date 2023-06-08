package io.sample.dataharvester.business.service;

import io.sample.dataharvester.model.Event;
import io.sample.dataharvester.model.UserEventInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventDetailService {

    private final ShowService showService;

    public UserEventInfo.EventDetail createEventDetail(Event event) {
        UserEventInfo.EventDetail eventDetail = new UserEventInfo.EventDetail();
        eventDetail.setPlatform(event.getShow().getPlatform());
        eventDetail.setShowTitle(event.getShow().getTitle());
        eventDetail.setFirstPersonInCast(showService.extractFirstPersonInCast(event.getShow().getCast()));
        eventDetail.setShowId(event.getShow().getShowId());
        eventDetail.setEventTime(event.getEventDate());

        return eventDetail;
    }
}
