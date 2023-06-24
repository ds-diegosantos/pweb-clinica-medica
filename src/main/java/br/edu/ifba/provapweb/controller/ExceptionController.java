package br.edu.ifba.provapweb.controller;

import br.edu.ifba.provapweb.domain.dto.response.ErroResponse;
import br.edu.ifba.provapweb.domain.exceptions.ResourceBadRequestException;
import br.edu.ifba.provapweb.domain.exceptions.ResourceConflictException;
import br.edu.ifba.provapweb.domain.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException rnfe, HttpServletRequest request){
        List<ErroResponse> listaErrors= new ArrayList<>();
        List<FieldError> fieldErrors = rnfe.getBindingResult().getFieldErrors();

        fieldErrors.forEach(
                e -> {
                    ErroResponse detalhes = new ErroResponse(e.getField(), e.getDefaultMessage(), new Date().getTime());
                    listaErrors.add(detalhes);
                });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(listaErrors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroResponse> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request) {
        ErroResponse erroResponse = new ErroResponse("Recurso não encontrado", rnfe.getMessage(),  new Date().getTime());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(erroResponse);
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<ErroResponse> handleResourceBadRequestException(ResourceBadRequestException rnfe, HttpServletRequest request) {
        ErroResponse erroResponse = new ErroResponse("requisição inválida", rnfe.getMessage(),  new Date().getTime());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erroResponse);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<?> handleResourceConflictException(ResourceConflictException rnfe, HttpServletRequest request) {
        ErroResponse erroResponse = new ErroResponse("Recurso resultou em um conflito", rnfe.getMessage(),  new Date().getTime());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(erroResponse);
    }



}
