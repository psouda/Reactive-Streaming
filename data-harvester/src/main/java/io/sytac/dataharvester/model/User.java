package io.sytac.dataharvester.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class User {
    private int id;
    @JsonAlias("first_name")
    private String firstName;
    @JsonAlias("last_name")
    private String lastName;
    @JsonAlias("date_of_birth")
    private String dateOfBirth;
    private String email;
    private String gender;
    @JsonAlias("ip_address")
    private String ipAddress;
    private String country;
}
