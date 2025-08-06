package com.client.ws.rasmooplus.exception.handler;

import com.client.ws.rasmooplus.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Obj.: Centralizar as exceptions > pega os erros que nossas controllers irão tratar com as HandlerException
public class ResourceHandler {

    // 'caçador' de exceção
    @ExceptionHandler(NotFoundException.class) // quando tivermos uma exceção do tipo NotFoundException, ele fará o seguinte tratamento
    public ResponseEntity<String> notFoundException(NotFoundException nfe) {
        String errorMessage = nfe.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
