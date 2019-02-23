package com.guess.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class UnauthorizedException extends AbstractThrowableProblem {

    public UnauthorizedException(String message) {
        super(ErrorType.UNAUTHORIZED, "Unauthorized", Status.UNAUTHORIZED, message);
    }
}
