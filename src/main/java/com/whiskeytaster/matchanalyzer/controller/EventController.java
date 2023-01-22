package com.whiskeytaster.matchanalyzer.controller;

import com.whiskeytaster.matchanalyzer.exception.EventsNumberException;
import com.whiskeytaster.matchanalyzer.exception.NotAcceptable;
import com.whiskeytaster.matchanalyzer.service.EventService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/event/showAll")
    public String getAll(Model model) {
        List<String> events = eventService.getEventsStringList();
        model.addAttribute("events", events);
        return "event/showall";
    }

    @GetMapping("/event/show{number}")
    public String getMostProbableResultsAsString(@NotNull Model model, @PathVariable("number") int number)
            throws EventsNumberException {
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

    @ExceptionHandler(EventsNumberException.class)
    public NotAcceptable handleEventsNumberException(EventsNumberException exc) {
        return new NotAcceptable();
    }

}
