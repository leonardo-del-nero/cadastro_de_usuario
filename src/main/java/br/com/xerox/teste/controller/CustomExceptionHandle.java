package br.com.xerox.teste.controller;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.xerox.teste.common.ExceptionMessage;
import br.com.xerox.teste.common.FieldMessage;
import br.com.xerox.teste.exception.RecordNotFoundException;

@ControllerAdvice
public class CustomExceptionHandle {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        // Implement your exception handling logic here
        return ResponseEntity.badRequest().body(new ExceptionMessage(
            new Date(), 
            "Erro de validação", 
            "Ver trace", 
            e.getBindingResult().getFieldErrors().stream().map(o -> new FieldMessage(o.getField(),o.getDefaultMessage())).collect(Collectors.toList())
        ));
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleRecordNotFound(RecordNotFoundException e) {
        // Implement your exception handling logic here
        return ResponseEntity.badRequest().body(new ExceptionMessage(
            new Date(),
            "Registro não encontrado",
            e.getMessage(), 
            null
        ));
    }

    // @ExceptionHandler(ConstraintDefinitionException.class)
    // public ResponseEntity<ExceptionMessage> handleConstraintDefinitionViolation(ConstraintDefinitionException e) {
    //     // Implement your exception handling logic here
    //     return ResponseEntity.badRequest().body(new ExceptionMessage(
    //         new Date(),
    //         "Constraint Definition Violation",
    //         e.getMessage(),
    //         null
    //     ));
    // }
            
}
