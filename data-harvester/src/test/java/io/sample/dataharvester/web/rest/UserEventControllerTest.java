package io.sample.dataharvester.web.rest;

import io.sample.dataharvester.model.UserEventInfo;
import io.sample.dataharvester.business.aggregator.UserEventAggregator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserEventController.class)
@ExtendWith(MockitoExtension.class)
class UserEventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserEventAggregator userEventAggregator;

    @Test
    void testGetUserEvents_success() throws Exception {
        // Given
        UserEventInfo userEventInfo = new UserEventInfo();
        userEventInfo.setUserId(1);
        userEventInfo.setName("Payam Soudachi");
        userEventInfo.setAge(30);

        List<UserEventInfo> userEventInfos = Collections.singletonList(userEventInfo);

        // When
        Mockito.when(userEventAggregator.getAggregatedEvents()).thenReturn(userEventInfos);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/user-events")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].name", is("Payam Soudachi")))
                .andExpect(jsonPath("$[0].age", is(30)));
    }
}