package io.sample.dataharvester.business;

import io.sample.dataharvester.business.aggregator.UserEventAggregator;
import io.sample.dataharvester.business.service.EventDetailService;
import io.sample.dataharvester.business.service.UserService;
import io.sample.dataharvester.model.Event;
import io.sample.dataharvester.model.User;
import io.sample.dataharvester.model.UserEventInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserEventAggregatorTest {

    @InjectMocks
    private UserEventAggregator userEventAggregator;

    @Mock
    private UserService userService;

    @Mock
    private EventDetailService eventDetailService;

    @Test
    void testAggregateEvent() {
        // Given
        Event event = new Event();
        User user = new User();
        user.setId(1);
        user.setFirstName("Payam");
        user.setLastName("Soudachi");
        user.setDateOfBirth("11/09/1992");
        event.setUser(user);

        UserEventInfo.EventDetail eventDetail = new UserEventInfo.EventDetail();
        eventDetail.setEventTime(LocalDateTime.now().toString());

        // When
        when(userService.calculateAge("11/09/1992")).thenReturn(30);
        when(eventDetailService.createEventDetail(any(Event.class))).thenReturn(eventDetail);

        userEventAggregator.aggregateEvent(event);

        // Then
        Collection<UserEventInfo> events = userEventAggregator.getAggregatedEvents();
        assertEquals(1, events.size());

        UserEventInfo userEventInfo = events.iterator().next();
        assertEquals(1, userEventInfo.getUserId());
        assertEquals("Payam Soudachi", userEventInfo.getName());
        assertEquals(30, userEventInfo.getAge());
    }

    @Test
    void testAggregateEvent_UserServiceException() {
        // Given
        Event event = new Event();
        User user = new User();
        user.setId(1);
        user.setFirstName("Payam");
        user.setLastName("Soudachi");
        user.setDateOfBirth("11/09/1992");
        event.setUser(user);

        // When
        when(userService.calculateAge("11/09/1992")).thenThrow(new RuntimeException("Test Exception"));

        // Then
        assertThrows(RuntimeException.class, () -> userEventAggregator.aggregateEvent(event));
    }
}