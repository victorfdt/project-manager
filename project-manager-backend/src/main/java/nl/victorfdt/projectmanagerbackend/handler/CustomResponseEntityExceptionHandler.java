package nl.victorfdt.projectmanagerbackend.handler;

import nl.victorfdt.projectmanagerbackend.exception.EntityNotFoundException;
import nl.victorfdt.projectmanagerbackend.exception.InvalidJwtAuthenticationException;
import nl.victorfdt.projectmanagerbackend.exception.UniqueKeyViolationException;
import nl.victorfdt.projectmanagerbackend.response.EntityNotFoundResponse;
import nl.victorfdt.projectmanagerbackend.response.UniqueKeyViolationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<?> handleUniqueKeyViolationException(UniqueKeyViolationException exception, WebRequest request) {
        var response = new UniqueKeyViolationResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest request) {
        var response = new EntityNotFoundResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<?> handleInvalidJwtAuthenticationException(EntityNotFoundException exception, WebRequest request) {
        var response = new InvalidJwtAuthenticationException(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
