package io.sytac.dataharvester.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class Event {
    @JsonAlias("event_date")
    private String eventDate;
    private User user;
    private Show show;
}
