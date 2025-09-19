package com.client.ws.rasmooplus.integration.impl; // integração com ws-raspay

import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.ws.rasmooplus.dto.wsraspay.OrderDto;
import com.client.ws.rasmooplus.dto.wsraspay.PaymentDto;
import com.client.ws.rasmooplus.integration.WsRaspayIntegration;
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
        return null;
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
