package io.sample.dataharvester.business.converter.impl;

import io.sample.dataharvester.business.converter.DateTimeConverter;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AmsterdamDateTimeConverter implements DateTimeConverter {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS");
    private static final ZoneId SOURCE_TIMEZONE = ZoneId.of("UTC");
    private static final ZoneId TARGET_TIMEZONE = ZoneId.of("Europe/Amsterdam");

    public String convertTimeZone(String eventDate) {
        // Parse the date-time string into a ZonedDateTime object
        ZonedDateTime dateTime = ZonedDateTime.parse(eventDate, DATE_TIME_FORMATTER.withZone(SOURCE_TIMEZONE));
        // Adjust the timezone
        dateTime = dateTime.withZoneSameInstant(TARGET_TIMEZONE);
        // Format the date-time back into a string and return
        return DATE_TIME_FORMATTER.format(dateTime);
    }
}
