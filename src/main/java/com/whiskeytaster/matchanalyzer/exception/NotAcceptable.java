package com.whiskeytaster.matchanalyzer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotAcceptable extends ResponseStatusException {

    public NotAcceptable() {
        super(HttpStatus.NOT_ACCEPTABLE, "Provided value must be greater than 0.");
    }
}
