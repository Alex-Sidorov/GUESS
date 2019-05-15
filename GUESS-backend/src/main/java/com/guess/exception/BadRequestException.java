package com.guess.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class BadRequestException extends AbstractThrowableProblem {

    public BadRequestException(String message) {
        super(ErrorType.BAD_REQUEST, "Bad Request", Status.BAD_REQUEST, message);
    }

}
