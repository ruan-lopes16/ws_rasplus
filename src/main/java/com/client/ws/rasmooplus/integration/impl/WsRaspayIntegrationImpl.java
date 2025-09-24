package com.client.ws.rasmooplus.integration.impl; // integração com ws-raspay

import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.ws.rasmooplus.dto.wsraspay.OrderDto;
import com.client.ws.rasmooplus.dto.wsraspay.PaymentDto;
import com.client.ws.rasmooplus.integration.WsRaspayIntegration;
import java.util.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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

        try {
            HttpHeaders headers = getHttpHeaders();

            // Criação da entidade HTTP contendo o DTO e os headers
            HttpEntity<CustomerDto> request = new HttpEntity<>(dto, headers);

            // response entity > retorno/resposta da chamada
            // Chamada HTTP POST para o serviço externo
            ResponseEntity<CustomerDto> response =              // no momento esperamos que a resposta seja o proprio dto
                    restTemplate.exchange(
                            "http://localhost:8081/ws-raspay/v1/customer",
                            HttpMethod.POST,
                            request,
                            CustomerDto.class
                    ); // url, verbo http, objeto de requisição, tipo de retorno

            return response.getBody(); // Retorna o corpo da resposta

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

    // isolando headers
    private static HttpHeaders getHttpHeaders() {
        // Criação dos headers HTTP
        HttpHeaders headers = new HttpHeaders();

        // Credenciais para autenticação básica
        String credential = "rasmooplus:r@sm00";

        // Codificação Base64 usando biblioteca padrão do Java
        String base64 = Base64.getEncoder().encodeToString(credential.getBytes());

        // Adicionando header de autorização Basic Auth
        headers.add("Authorization", "Basic " + base64);
        return headers;
    }
}
