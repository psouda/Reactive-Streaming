package io.sample.dataharvester.business.manager;

import io.sample.dataharvester.business.aggregator.UserEventAggregator;
import io.sample.dataharvester.business.service.EventStreamService;
import io.sample.dataharvester.config.StreamingProperties;
import io.sample.dataharvester.model.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StreamingEventManagerTest {

    @InjectMocks
    private StreamingEventManager streamingEventManager;

    @Mock
    private EventStreamService streamingService;

    @Mock
    private StreamingProperties streamingProperties;

    @Mock
    private UserEventAggregator aggregatedEventService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testStartAggregating_HappyPath() {
        // Arrange
        Event event = new Event();
        when(streamingProperties.getLimitationInSeconds()).thenReturn(20);
        when(streamingService.streamEvents(any(), any(), any())).thenReturn(Flux.just(new Event()));
        when(streamingProperties.getPaths()).thenReturn(new StreamingProperties.Paths());

        // Act
        streamingEventManager.startAggregating();

        verify(streamingService, times(3)).streamEvents(any(), any(), any());
        verify(aggregatedEventService, times(3)).aggregateEvent(event);
    }

}