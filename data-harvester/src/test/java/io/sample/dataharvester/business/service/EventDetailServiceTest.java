package io.sample.dataharvester.business.service;

import io.sample.dataharvester.model.Event;
import io.sample.dataharvester.model.Show;
import io.sample.dataharvester.model.UserEventInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventDetailServiceTest {

    @Mock
    private ShowService showService;

    @InjectMocks
    private EventDetailService eventDetailService;

    private Event event;
    private UserEventInfo.EventDetail eventDetail;

    @BeforeEach
    public void setUp() {
        event = new Event();
        Show show = new Show();
        show.setPlatform("Netflix");
        show.setTitle("Games of Thrones");
        show.setCast("Payam Soudachi");
        show.setShowId("s3");
        event.setShow(show);
        event.setEventDate("14-05-2023 14:56:54.003");
    }

    @Test
    void createEventDetail_HappyPath() {
        when(showService.extractFirstPersonInCast(event.getShow().getCast())).thenReturn("Payam Soudachi");

        eventDetail = eventDetailService.createEventDetail(event);

        assertEquals("Netflix", eventDetail.getPlatform());
        assertEquals("Games of Thrones", eventDetail.getShowTitle());
        assertEquals("Payam Soudachi", eventDetail.getFirstPersonInCast());
        assertEquals("s3", eventDetail.getShowId());
        assertEquals("14-05-2023 14:56:54.003", eventDetail.getEventTime());
    }

    @Test
    void createEventDetail_NullCast() {
        event.getShow().setCast(null);
        when(showService.extractFirstPersonInCast(null)).thenReturn(null);

        eventDetail = eventDetailService.createEventDetail(event);

        assertEquals("Netflix", eventDetail.getPlatform());
        assertEquals("Games of Thrones", eventDetail.getShowTitle());
        assertNull(eventDetail.getFirstPersonInCast());
        assertEquals("s3", eventDetail.getShowId());
        assertEquals("14-05-2023 14:56:54.003", eventDetail.getEventTime());
    }
}
