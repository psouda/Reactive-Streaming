package io.sample.dataharvester.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserEventInfo {
    private int userId;
    private String name;
    private int age;
    private List<EventDetail> events = new ArrayList<>();

    @Data
    public static class EventDetail {
        private String platform;
        private String showTitle;
        private String firstPersonInCast;
        private String showId;
        private String eventTime;
    }
}