package com.guess.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class UnprocessableEntity extends AbstractThrowableProblem {

    public UnprocessableEntity(String message) {
        super(ErrorType.UNPROCESSABLE_ENTITY, "Unprocessable Entity", Status.UNPROCESSABLE_ENTITY, message);
    }
}
