package io.sample.dataharvester.business.converter.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class AmsterdamDateTimeConverterTest {

    private AmsterdamDateTimeConverter dateTimeConverter;

    @BeforeEach
    public void setUp() {
        dateTimeConverter = new AmsterdamDateTimeConverter();
    }

    @Test
    void testConvertTimeZone() {
        String sourceDateTime = "15-05-2023 09:00:00.000";
        String expectedTargetDateTime = "15-05-2023 11:00:00.000";

        String actualTargetDateTime = dateTimeConverter.convertTimeZone(sourceDateTime);

        assertEquals(expectedTargetDateTime, actualTargetDateTime);
    }

    @ParameterizedTest
    @ValueSource(strings = {"01/01/2023 12:00:00.000",
            "32-01-2023 12:00:00.000",
            "This is not a date-time string"
    })
    void testConvertTimeZoneWithInvalidDateFormat(String sourceDateTime) {
        assertThrows(DateTimeParseException.class, () -> dateTimeConverter.convertTimeZone(sourceDateTime));
    }

    @Test
    void testConvertTimeZoneWithNull() {
        String sourceDateTime = null;

        assertThrows(NullPointerException.class, () -> dateTimeConverter.convertTimeZone(sourceDateTime));
    }
}
