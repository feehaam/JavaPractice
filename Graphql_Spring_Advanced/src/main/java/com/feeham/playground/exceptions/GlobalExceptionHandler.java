package com.feeham.playground.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({CustomException.class})
    public ErrorResponse handleCustomException(Exception e, HttpServletRequest request) {
        return new ErrorResponse(
                e.getClass().getSimpleName(),
                "An error occurred while performing a database operation",
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                new Date(),
                request.getRequestURI()
        );
    }
}
