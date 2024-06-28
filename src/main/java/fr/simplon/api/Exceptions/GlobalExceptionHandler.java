package fr.simplon.api.Exceptions;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import lombok.Getter;
import lombok.Setter;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<?> handleInvalidCredentialException(InvalidCredentialException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(ex.getClass().getName(), ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(ex.getClass().getName(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setType(ex.getClass().getName());
        errorDetails.setMessage(ex.getLocalizedMessage());
        return new ResponseEntity<>(errorDetails, resolveAnnotationResponseStatus(ex));
    }

    private HttpStatus resolveAnnotationResponseStatus(Exception ex) {
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        return responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
    }
}

@Getter
@Setter
class ErrorDetails {
    String type;
    String message;

    public ErrorDetails() {}

    public ErrorDetails(String type, String message) {
        this.type = type;
        this.message = message;
    }
}
