package com.client.ws.rasmooplus.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor

public class ErrorResponseDto {     // dto para cuidar dos erros > padronização

    private String message;
    private HttpStatus httpStatus; // indicar o status. Ex.: OK
    private Integer statusCode;     // indicar o código do status. Ex.: 204
}
