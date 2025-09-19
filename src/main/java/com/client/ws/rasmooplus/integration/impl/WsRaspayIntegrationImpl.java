package com.client.ws.rasmooplus.integration.impl; // integração com ws-raspay

import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.ws.rasmooplus.dto.wsraspay.OrderDto;
import com.client.ws.rasmooplus.dto.wsraspay.PaymentDto;
import com.client.ws.rasmooplus.integration.WsRaspayIntegration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// transformando em um component ou bean
@Component      // por se tratar de camada de negocio > camada de integração
public class WsRaspayIntegrationImpl implements WsRaspayIntegration {

    // instanciando/fazendo comunicação http com o serviço externo
    private RestTemplate restTemplate;

    public WsRaspayIntegrationImpl(){
        restTemplate = new RestTemplate();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto dto) {
        // criação de http para enviar o body
        HttpEntity<CustomerDto> request = new HttpEntity<>(dto); // não consegue passar como atributo, pois cada HttpEntity precisa de um determinado DTO

        try {
            // response entity > retorno/resposta da chamada

            ResponseEntity<CustomerDto> response =              // no momento esperamos que a resposta seja o proprio dto
                    restTemplate.exchange(
                            "http://localhost:8081/ws-raspay/v1/costumer",
                            HttpMethod.POST,
                            request,
                            CustomerDto.class
                    ); // url, verbo http, objeto de requisição, tipo de retorno

            return response.getBody();

        } catch (Exception exception) {
            throw exception;

        }

    }

    @Override
    public OrderDto createOrder(OrderDto dto) {
        return null;
    }

    @Override
    public Boolean processPayment(PaymentDto dto) {
        return null;
    }
}
