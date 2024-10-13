package com.ecommerce.notificationservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UpdateFailedException extends RuntimeException {
    public UpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
