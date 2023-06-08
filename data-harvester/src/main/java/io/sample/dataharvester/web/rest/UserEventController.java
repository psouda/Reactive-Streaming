package io.sample.dataharvester.web.rest;

import io.sample.dataharvester.business.aggregator.UserEventAggregator;
import io.sample.dataharvester.model.UserEventInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class UserEventController {

    private final UserEventAggregator userEventAggregator;

    @GetMapping("/user-events")
    public ResponseEntity<Collection<UserEventInfo>> getUserEvents() {
        return ResponseEntity.ok(userEventAggregator.getAggregatedEvents());
    }
}
