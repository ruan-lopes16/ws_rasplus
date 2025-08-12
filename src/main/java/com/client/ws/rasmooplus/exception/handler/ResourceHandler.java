package com.client.ws.rasmooplus.exception.handler;

import com.client.ws.rasmooplus.dto.error.ErrorResponseDto;
import com.client.ws.rasmooplus.exception.BadRequestException;
import com.client.ws.rasmooplus.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Obj.: Centralizar as exceptions > pega os erros que nossas controllers irão tratar com as HandlerException
public class ResourceHandler {

    // 'caçador' de exceção
    @ExceptionHandler(NotFoundException.class) // quando tivermos uma exceção do tipo NotFoundException, ele fará o seguinte tratamento
    public ResponseEntity<ErrorResponseDto> notFoundException(NotFoundException nfe) { // agora não será passado mais uma String qualquer > agora queremos algo mais padronizado(ErrorResponseDto)

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseDto.builder()      // builder >>> iremos construi-lo(JSON) apartir deste método
                        .message(nfe.getMessage())                      // pegando mensagem que irá ser ixibida
                        .httpStatus(HttpStatus.NOT_FOUND)               // indicando qual erro deve apresentar
                        .statusCode(HttpStatus.NOT_FOUND.value())       // pegando numero código do status da requisição `.value()`
                .build());
    }

    @ExceptionHandler(BadRequestException.class) // quando tivermos uma exceção do tipo BadRequestException, ele fará o seguinte tratamento
    public ResponseEntity<ErrorResponseDto> badRequestException(BadRequestException bre) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.builder()      // builder >>> iremos construi-lo(JSON) apartir deste método
                        .message(bre.getMessage())                      // pegando mensagem que irá ser ixibida
                        .httpStatus(HttpStatus.BAD_REQUEST)               // indicando qual erro deve apresentar
                        .statusCode(HttpStatus.BAD_REQUEST.value())       // pegando numero código do status da requisição `.value()`
                .build());
    }

    // HttpStatus no return > é exbidido na parte de cima do Postman
    // os outros HttpStatus no builder > é exibido no nosso body do JSON
}
