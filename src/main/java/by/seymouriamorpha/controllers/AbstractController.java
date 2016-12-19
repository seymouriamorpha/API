package by.seymouriamorpha.controllers;

import by.seymouriamorpha.beans.errors.Error;
import by.seymouriamorpha.beans.errors.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Raman_Susla on 12/14/2016.
 */
public interface AbstractController
{
    default ResponseEntity<Object> error(final String errorMessage, final HttpStatus httpStatus)
    {
        final Error error = new Error(ErrorMessages.REJECTED);
        error.setMessage(errorMessage);
        return new ResponseEntity<>(error, httpStatus);
    }
}