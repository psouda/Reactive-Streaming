package io.sample.dataharvester.business.deserializer.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sample.dataharvester.business.converter.DateTimeConverter;
import io.sample.dataharvester.business.deserializer.EventDeserializer;
import io.sample.dataharvester.model.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultEventDeserializer implements EventDeserializer {

    private final ObjectMapper objectMapper;
    private final DateTimeConverter dateTimeConverter;

    public Event deserialize(String data) {
        try {
            Event event = objectMapper.readValue(data, Event.class);
            String convertedDate = dateTimeConverter.convertTimeZone(event.getEventDate());
            event.setEventDate(convertedDate);
            return event;
        } catch (JsonProcessingException exception) {
            // Skip the event and log the error
            log.error("Skipping event due to parsing error: {}", exception.getMessage());
            return null;
        }
    }
}
