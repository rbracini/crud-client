package tech.curtiu.crud_client.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import tech.curtiu.crud_client.dtos.CustomException;
import tech.curtiu.crud_client.dtos.ValidationError;
import tech.curtiu.crud_client.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomException> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomException customException = new CustomException(Instant.now(), status.value(), e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(customException);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomException> methodArgumentNotValid(MethodArgumentNotValidException e,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError error = new ValidationError(Instant.now(), status.value(), "Dados inválidos",
                request.getRequestURI());
        e.getBindingResult().getFieldErrors().forEach(fe -> error.addError(fe.getField(), fe.getDefaultMessage()));
        return ResponseEntity.status(status).body(error);
    }

}
