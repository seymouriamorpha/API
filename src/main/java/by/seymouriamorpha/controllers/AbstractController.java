package by.seymouriamorpha.controllers;

import by.seymouriamorpha.beans.errors.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * catmo_000 API:12/14/2016.
 */
public interface AbstractController {
    default ResponseEntity<Object> error(final String errorMessage, final HttpStatus httpStatus) {
        final Error error = new Error();
        error.setMessage(errorMessage);
        return new ResponseEntity<>(error, httpStatus);
    }
}
