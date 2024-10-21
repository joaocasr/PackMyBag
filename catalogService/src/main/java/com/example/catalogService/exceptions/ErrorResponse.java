package com.example.catalogService.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import java.util.Date;

public class ErrorResponse {
    // customizing timestamp serialization format
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private int status;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String statusMsg;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String message;

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this.timestamp = new Date();
        this.status = httpStatus.value();
        this.statusMsg = httpStatus.name();
        this.message = message;
    }

}