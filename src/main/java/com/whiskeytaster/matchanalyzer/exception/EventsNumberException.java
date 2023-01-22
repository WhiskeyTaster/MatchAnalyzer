package com.whiskeytaster.matchanalyzer.exception;

public class EventsNumberException extends RuntimeException{
    public EventsNumberException() {
        super();
    }

    public EventsNumberException(String message) {
        super(message);
    }

    public EventsNumberException(Throwable throwable) {
        super(throwable);
    }

    public EventsNumberException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
