package org.sewas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by sebastian on 15/06/17.
 */
@ControllerAdvice
public class LeagueTableExceptionHandler {

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Requested season is not available.")
    @ExceptionHandler(SeasonNotAvailableException.class)
    public void handleIOException(){
        // 404
    }

}
