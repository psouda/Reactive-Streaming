package io.sample.dataharvester.business.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.sample.dataharvester.business.converter.DateTimeConverter;
import io.sample.dataharvester.model.Event;
import io.sample.dataharvester.business.deserializer.impl.DefaultEventDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class EventDeserializerTest {

    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private DateTimeConverter dateTimeConverter;

    private EventDeserializer eventDeserializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        eventDeserializer = new DefaultEventDeserializer(objectMapper, dateTimeConverter);
    }

    @Test
    void deserializeHappyPath() throws Exception {
        String data = "{}";
        Event mockEvent = new Event();
        mockEvent.setEventDate("2023-05-14T11:45:00.000Z");
        when(objectMapper.readValue(data, Event.class)).thenReturn(mockEvent);
        when(dateTimeConverter.convertTimeZone(mockEvent.getEventDate())).thenReturn("15-05-2023 11:20:16.397");

        Event event = eventDeserializer.deserialize(data);

        assertNotNull(event);
        assertEquals("15-05-2023 11:20:16.397", event.getEventDate());
    }
}