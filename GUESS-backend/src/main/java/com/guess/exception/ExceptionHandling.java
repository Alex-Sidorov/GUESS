package com.guess.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.DefaultProblem;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.violations.ConstraintViolationProblem;

@ControllerAdvice
public class ExceptionHandling implements ProblemHandling {

    @Override
    public ResponseEntity<Problem> process(ResponseEntity<Problem> entity, NativeWebRequest request) {

        Problem problem = entity.getBody();
        if (!(problem instanceof ConstraintViolationProblem || problem instanceof DefaultProblem)) {
            return entity;
        }

        ProblemBuilder problemBuilder = Problem.builder()
                .withType(Problem.DEFAULT_TYPE.equals(problem.getType()) ? ErrorType.DEFAULT : problem.getType())
                .withTitle(problem.getTitle())
                .withStatus(problem.getStatus());

        if (problem instanceof ConstraintViolationProblem) {
            problemBuilder.withType(ErrorType.CONSTRAINT_VIOLATION);
            problemBuilder.with("violations", ((ConstraintViolationProblem) problem).getViolations());
        } else {
            problemBuilder
                    .withDetail(problem.getDetail())
                    .withInstance(problem.getInstance())
                    .withCause(((DefaultProblem) problem).getCause());
            problem.getParameters().forEach(problemBuilder::with);
        }

        return new ResponseEntity<>(problemBuilder.build(), entity.getHeaders(), entity.getStatusCode());
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handle(InvalidFormatException ex, NativeWebRequest request) {
        return create(Status.UNPROCESSABLE_ENTITY, ex, request, ErrorType.UNPROCESSABLE_ENTITY);
    }
}
