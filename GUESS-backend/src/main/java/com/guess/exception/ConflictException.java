package com.guess.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class ConflictException extends AbstractThrowableProblem {

    public ConflictException(String message) {
        super(ErrorType.CONFLICT, "Conflict", Status.CONFLICT, message);
    }

}
