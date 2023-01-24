package com.whiskeytaster.matchanalyzer.controller;

import com.whiskeytaster.matchanalyzer.service.EventService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(EventController.class)
public class EventControllerTest {

    @MockBean
    private EventService eventService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/event/showAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("event/show_events"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("events"));
    }

    @Test
    public void getTeamsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/event/teams"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("event/show_teams"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("teams"));
    }

    @Test
    public void getMostProbableResultsAsStringTest_TwoResults() throws Exception {
        when(eventService.getMostProbableResultsAsString(2)).thenReturn(List.of("123", "abc"));

        mockMvc.perform(MockMvcRequestBuilders.get("/event/show2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("event/show_events"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("events"))
                .andExpect(MockMvcResultMatchers.model().attribute("events", Matchers.contains("123", "abc")));
    }

    @Test
    public void getMostProbableResultsAsStringTest_ZeroResults() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/event/show0"))
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable());
    }

}
