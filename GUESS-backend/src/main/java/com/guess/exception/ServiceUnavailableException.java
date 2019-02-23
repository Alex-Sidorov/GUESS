package com.guess.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class ServiceUnavailableException extends AbstractThrowableProblem {

    public ServiceUnavailableException(String message) {
        super(ErrorType.SERVICE_UNAVAILABLE, "Service Unavailable", Status.SERVICE_UNAVAILABLE, message);
    }
}
