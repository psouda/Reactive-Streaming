package io.sample.dataharvester.business.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void seUp(){
        userService = new UserService();
    }

    @Test
    void testCalculateAge() {
        String dateOfBirth = "11/09/1992";
        int expectedAge = 30;

        int actualAge = userService.calculateAge(dateOfBirth);

        assertEquals(expectedAge, actualAge);
    }

}