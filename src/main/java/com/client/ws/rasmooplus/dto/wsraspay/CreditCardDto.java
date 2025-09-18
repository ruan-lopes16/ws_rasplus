package com.client.ws.rasmooplus.dto.wsraspay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDto {

    private String number;

    private String documentNumber;

    private Long cvv;

    private Long month;

    private Long year;

    private Long installments; // numero de parcelas
}
