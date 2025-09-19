package com.client.ws.rasmooplus.integration; // integração com ws-raspay

import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.ws.rasmooplus.dto.wsraspay.OrderDto;
import com.client.ws.rasmooplus.dto.wsraspay.PaymentDto;

public interface WsRaspayIntegration {

    // criar customer/client
    CustomerDto createCustomer(CustomerDto dto);

    // criar pedido
    OrderDto createOrder(OrderDto dto);

    // realizar pagamento
    Boolean processPayment(PaymentDto dto);

}
