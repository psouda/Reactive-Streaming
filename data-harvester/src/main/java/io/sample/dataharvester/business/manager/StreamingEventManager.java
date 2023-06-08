package io.sample.dataharvester.business.manager;

import io.sample.dataharvester.business.aggregator.UserEventAggregator;
import io.sample.dataharvester.business.service.EventStreamService;
import io.sample.dataharvester.business.stopcondition.HarvestStopCondition;
import io.sample.dataharvester.config.StreamingProperties;
import io.sample.dataharvester.model.Event;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class StreamingEventManager {

    private final EventStreamService streamingService;
    private final StreamingProperties streamingProperties;
    private final HarvestStopCondition stopCondition;
    private final UserEventAggregator aggregatedEventService;

    @PostConstruct
    public void startAggregating() {
        Duration duration = Duration.ofSeconds(streamingProperties.getLimitationInSeconds());

        Flux<Event> eventsStream = Flux.concat(
                streamingService.streamEvents(streamingProperties.getPaths().getNetflix(), stopCondition, duration),
                streamingService.streamEvents(streamingProperties.getPaths().getSytazon(), stopCondition, duration),
                streamingService.streamEvents(streamingProperties.getPaths().getSysney(), stopCondition, duration)
        );

        eventsStream.doOnNext(aggregatedEventService::aggregateEvent)
                .subscribe();
    }
}