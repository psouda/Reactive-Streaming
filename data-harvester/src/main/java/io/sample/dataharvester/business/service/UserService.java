package io.sample.dataharvester.business.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
public class UserService {

    private static final DateTimeFormatter BIRTHDATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public int calculateAge(String dateOfBirth) {
        LocalDate birthDate = LocalDate.parse(dateOfBirth, BIRTHDATE_FORMATTER);
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
