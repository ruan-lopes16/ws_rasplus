package com.client.ws.rasmooplus.exception.handler;

import com.client.ws.rasmooplus.dto.error.ErrorResponseDto;
import com.client.ws.rasmooplus.exception.BadRequestException;
import com.client.ws.rasmooplus.exception.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(MethodArgumentNotValidException.class) // quando tivermos uma exceção do tipo MethodArgumentNotValidException, ele fará o seguinte tratamento
    public ResponseEntity<ErrorResponseDto> badRequestException(MethodArgumentNotValidException manv) {
        // nos permite entrar no escopo de errors
        // percorrendo exceção até encontrar os campos e mensagens de erro

        Map<String, String> messages = new HashMap<>(); // criado um Map para guardar a primeira chave (ex.: name) e segunda chave (ex.: mensagem do erro)
                                                        // e por fim cria de fato uma especie de dicionário/caderno para as anotações de erros que virão

        /* GEMINI
essa linha de código cria um dicionário vazio para guardar as mensagens de erro.
A ideia é que, para cada erro encontrado pelo getBindingResult(), você possa armazenar a mensagem de forma organizada,
associando o nome do campo à sua respectiva mensagem de erro
 */
        manv.getBindingResult().getAllErrors().forEach(error -> { // anotando e listando os erros
            String field = ((FieldError) error).getField(); // identificando cada campo erro
            String defaultMessage = error.getDefaultMessage(); // por fim coleta a mensagem padrão

            messages.put(field, defaultMessage);    // isso é me retornado em forma de array > pois foi armazenado em um array
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.builder()
                .message(Arrays.toString(messages.entrySet().toArray())) // array simples com os campos e nomes(chave-valor > ["name"="bla bla bla"]) e os transforma em uma unica string
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class) // quando tivermos uma exceção do tipo BadRequestException, ele fará o seguinte tratamento
    public ResponseEntity<ErrorResponseDto> badRequestException(DataIntegrityViolationException dataIntegrityViolationException) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.builder()      // builder >>> iremos construi-lo(JSON) apartir deste método
                .message(dataIntegrityViolationException.getMessage())                      // pegando mensagem que irá ser ixibida
                .httpStatus(HttpStatus.BAD_REQUEST)               // indicando qual erro deve apresentar
                .statusCode(HttpStatus.BAD_REQUEST.value())       // pegando numero código do status da requisição `.value()`
                .build());
    }
}
