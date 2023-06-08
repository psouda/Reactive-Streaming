package io.sample.dataharvester.business.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowServiceTest {

    private ShowService showService;

    @BeforeEach
    void setUp() {
        showService = new ShowService();
    }

    @Test
    void testExtractFirstPersonInCastWithMultipleCasts() {
        String cast = "Brad Pitt, Payam Soudachi, Robert De Niro";
        String expectedFirstPerson = "Brad Pitt";

        String actualFirstPerson = showService.extractFirstPersonInCast(cast);

        assertEquals(expectedFirstPerson, actualFirstPerson);
    }

    @Test
    void testExtractFirstPersonInCastWithCast() {
        String cast = "Brad Pitt";
        String expectedFirstPerson = "Brad Pitt";

        String actualFirstPerson = showService.extractFirstPersonInCast(cast);

        assertEquals(expectedFirstPerson, actualFirstPerson);
    }
}