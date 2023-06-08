package io.sample.dataharvester.business.stopcondition.impl;

import io.sample.dataharvester.business.stopcondition.HarvestStopCondition;
import io.sample.dataharvester.model.Event;
import io.sample.dataharvester.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserSpecificStopConditionTest {

    private HarvestStopCondition stopCondition;

    @BeforeEach
    void setUp() {
        stopCondition = new UserSpecificStopCondition();
    }

    @Test
    void evaluate_WithNonSampleUser_ReturnsFalse() {
        User user = new User();
        user.setFirstName("Payam");
        Event event = new Event();
        event.setUser(user);

        boolean result = stopCondition.evaluate(event);
        assertFalse(result);
    }

    @Test
    void evaluate_WithSampleUser_ReachingRepeatingLimit_ReturnsTrue() {
        User user = new User();
        user.setFirstName("Sample1");
        Event event = new Event();
        event.setUser(user);

        // First two events with Sample1 user
        boolean result1 = stopCondition.evaluate(event);
        assertFalse(result1);
        boolean result2 = stopCondition.evaluate(event);
        assertFalse(result2);

        // Reaching the repeating limit
        boolean result3 = stopCondition.evaluate(event);
        assertTrue(result3);
    }
}
