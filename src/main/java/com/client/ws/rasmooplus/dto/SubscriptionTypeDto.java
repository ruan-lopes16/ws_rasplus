package com.client.ws.rasmooplus.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionTypeDto {
    private Long id;

    @NotBlank(message = "cannot be null or empty") // não nulo e nem vazio
    @Size(min = 5, max = 30, message = "must be between 5 and 30 characters long") // tem que ter de 5 a 30 caracteres
    private String name;

    @Max(value = 12, message = "cannot be greater than 12") // me permite colocar o máximo permitido, neste caso coloca-se value para colocar a quantidade + uma mensagem a ser exibida
    private Long accessMonths;

    @NotNull(message = "cannot be null" )
    private BigDecimal price;

    @NotBlank(message = "cannot be null or empty")
    @Size(min = 5, max = 15, message = "must be between 5 and 15 characters long")
    private String productKey;
}
