package io.sample.dataharvester.business.service;

import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ShowService {

    public String extractFirstPersonInCast(String cast) {
        return Optional.ofNullable(cast)
                .filter(s -> !s.isEmpty())
                .map(s -> s.split(",")[0].trim())
                .orElse(null);
    }
}
