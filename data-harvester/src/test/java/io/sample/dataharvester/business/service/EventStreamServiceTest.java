package io.sample.dataharvester.business.service;

import io.sample.dataharvester.business.stopcondition.HarvestStopCondition;
import io.sample.dataharvester.business.deserializer.EventDeserializer;
import io.sample.dataharvester.model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EventStreamServiceTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Mock
    private EventDeserializer eventDeserializer;

    @Mock
    private HarvestStopCondition stopCondition;

    private EventStreamService eventStreamService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(String.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToFlux(String.class)).thenReturn(Flux.just("test data"));
        when(eventDeserializer.deserialize(any(String.class))).thenReturn(new Event());
        when(stopCondition.evaluate(any())).thenReturn(false);
        eventStreamService = new EventStreamService(webClient, eventDeserializer);
    }

    @Test
    void testStreamEvents() {
        Flux<Event> eventFlux = eventStreamService.streamEvents("http://localhost:8080", stopCondition, Duration.ofSeconds(30));
        StepVerifier.create(eventFlux)
                .expectNextCount(1)
                .verifyComplete();
    }
}