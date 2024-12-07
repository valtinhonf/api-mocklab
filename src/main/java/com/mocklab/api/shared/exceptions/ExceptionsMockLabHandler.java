package com.mocklab.api.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class ExceptionsMockLabHandler {

    @ExceptionHandler(MockIsThisUser.class)
    protected ResponseEntity<Object> MockIsNotOfTheUser(MockIsThisUser e){
        String errorMessage =e.getLocalizedMessage();
        if (Objects.isNull(errorMessage) || errorMessage.equals("")){
            errorMessage = "The related Mock does not belong to the user!";
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(LocalDateTime.now(), errorMessage));
    }

}
