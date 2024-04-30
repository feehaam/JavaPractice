package com.feeham.playground.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String exception;
    private String message;
    private String status;
    private Date timeStamp;
    private String apiPath;
}