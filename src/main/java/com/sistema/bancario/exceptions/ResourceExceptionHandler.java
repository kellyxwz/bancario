package com.sistema.bancario.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandError> resourceNotFound (ResourceNotFoundException e, HttpServletRequest request){
        String erro = "Recurso não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandError err = new StandError(
                Instant.now(),
                status.value(),
                erro,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }


    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandError> database (DatabaseException e, HttpServletRequest request){
        String erro = "Erro de banco de dados";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandError err = new StandError(
                Instant.now(),
                status.value(),
                erro,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }




}
