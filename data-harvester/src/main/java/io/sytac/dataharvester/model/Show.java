package io.sytac.dataharvester.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class Show {
    @JsonAlias("show_id")
    private String showId;
    private String cast;
    private String country;
    @JsonAlias("date_added")
    private String dateAdded;
    private String description;
    private String director;
    private String duration;
    @JsonAlias("listed_in")
    private String listedIn;
    private String rating;
    @JsonAlias("release_year")
    private int releaseYear;
    private String title;
    private String type;
    private String platform;
}
