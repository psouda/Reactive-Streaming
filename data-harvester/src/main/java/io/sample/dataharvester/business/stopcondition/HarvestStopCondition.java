package io.sample.dataharvester.business.stopcondition;

import io.sample.dataharvester.model.Event;

public interface HarvestStopCondition {

    boolean evaluate(Event event);
}
