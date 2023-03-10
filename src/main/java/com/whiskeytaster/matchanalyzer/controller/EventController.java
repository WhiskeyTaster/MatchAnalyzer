package com.whiskeytaster.matchanalyzer.controller;

import com.whiskeytaster.matchanalyzer.exception.InvalidEventsNumberException;
import com.whiskeytaster.matchanalyzer.exception.LoadingInputDataException;
import com.whiskeytaster.matchanalyzer.service.EventService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("/event/load_{dataName}")
    public String loadData(Model model, @PathVariable("dataName") String data) {
        eventService.loadDataFromFile(data);
        getAll(model);
        return "event/show_events";
    }

    @GetMapping("/event/show")
    public String getAll(Model model) {
        List<String> events = eventService.getEventsStringList();
        model.addAttribute("events", events);
        return "event/show_events";
    }

    @GetMapping("/event/show{number}")
    public String getMostProbableResultsAsString(@NotNull Model model, @PathVariable("number") int number)
            throws InvalidEventsNumberException {
        List<String> events = eventService.getMostProbableResultsAsString(number);
        model.addAttribute("events", events);
        return "event/show_events";
    }

    @GetMapping("/event/teams")
    public String getTeams(Model model) {
        List<String> teams = eventService.getTeamNamesAlphabetically();
        model.addAttribute("teams", teams);
        return "event/show_teams";
    }

    @ExceptionHandler(value = InvalidEventsNumberException.class)
    public ResponseEntity<?> handleEventsNumberException(InvalidEventsNumberException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = LoadingInputDataException.class)
    public ResponseEntity<?> handleLoadingInputDataException(LoadingInputDataException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException exc) {
        return new ResponseEntity<>("Data not loaded", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
