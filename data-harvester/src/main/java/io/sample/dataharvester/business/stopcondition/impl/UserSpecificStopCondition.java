package io.sample.dataharvester.business.stopcondition.impl;

import io.sample.dataharvester.business.stopcondition.HarvestStopCondition;
import io.sample.dataharvester.model.Event;
import io.sample.dataharvester.model.User;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserSpecificStopCondition implements HarvestStopCondition {
    private static final String SAMPLE = "Sample1";
    private static final int REPEATING_LIMIT = 3;
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public boolean evaluate(Event event) {
        User user = event.getUser();
        if (user != null && SAMPLE.equals(user.getFirstName())) {
            return counter.incrementAndGet() >= REPEATING_LIMIT;
        }
        return false;
    }
}
