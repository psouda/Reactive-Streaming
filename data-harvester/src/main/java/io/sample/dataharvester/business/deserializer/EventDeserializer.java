package io.sample.dataharvester.business.deserializer;

import io.sample.dataharvester.model.Event;

public interface EventDeserializer {
    Event deserialize(String data);
}
