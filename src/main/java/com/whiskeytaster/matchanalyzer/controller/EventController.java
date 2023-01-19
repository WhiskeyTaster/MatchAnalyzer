package com.whiskeytaster.matchanalyzer.controller;

import com.whiskeytaster.matchanalyzer.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventController {
    private EventService eventService;

    @Autowired
    public EventController(final EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/event/showAll")
    public String getAll(Model model) {
        List<String> events = eventService.getEventsStringList();
        model.addAttribute("events", events);
        return "event/showall";
    }

    @GetMapping("/event/show{number}")
    public String getMostProbableResultsAsString(Model model, @PathVariable("number") int number) {
        List<String> events = eventService.getMostProbableResultsAsString(number);
        model.addAttribute("events", events);
        return "event/showall";
    }

    @GetMapping("/event/teams")
    public String getTeams(Model model) {
        List<String> teams = eventService.getTeamNamesAlphabetically();
        model.addAttribute("events", teams);
        return "event/showall";
    }
}
