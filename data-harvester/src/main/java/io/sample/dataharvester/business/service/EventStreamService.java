package io.sample.dataharvester.business.service;

import io.sample.dataharvester.business.stopcondition.HarvestStopCondition;
import io.sample.dataharvester.model.Event;
import io.sample.dataharvester.business.deserializer.EventDeserializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventStreamService {

    private final WebClient webClient;
    private final EventDeserializer eventDeserializer;

    public Flux<Event> streamEvents(String url, HarvestStopCondition stopCondition, Duration duration) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(String.class)
                .mapNotNull(eventDeserializer::deserialize)
                .filter(Objects::nonNull)
                .take(duration)
                .takeUntil(stopCondition::evaluate);
    }
}
