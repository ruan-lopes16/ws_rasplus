package com.client.ws.rasmooplus.controller;

import com.client.ws.rasmooplus.model.SubscriptionType;
import com.client.ws.rasmooplus.repository.SubscriptionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Dizendo que é um controller > Spring irá gerenciar esta classe como um componente
@RestController
@RequestMapping("/subscriptions-type") // mapeia a URL base para as requisições
public class SubscriptionTypeController { // qualquer método aqui só será executado caso tenha a URL /subscriptions-type

    // Injeção de dependência. O Spring irá criar uma instância de SubscriptionTypeRepository
    // e a "injetar" nesta variável, tornando-a disponível para uso.
    @Autowired
    private SubscriptionTypeRepository subscriptionTypeRepository; // BANCO

    @GetMapping()   // dizendo que é uma requisição do tipo GET
    // O método retorna um 'ResponseEntity', permitindo um controle total da resposta HTTP.
    // O tipo de retorno é uma lista de objetos 'SubscriptionType'
    public ResponseEntity<List<SubscriptionType>> findAll(){
        // O método 'findAll' do repositório busca todos os registros na tabela 'subscriptions_type'.
        List<SubscriptionType> subscriptionTypes = subscriptionTypeRepository.findAll();

        // Constrói e retorna a resposta HTTP.
        // ResponseEntity.status(HttpStatus.OK): Define o código de status HTTP como 200 (OK) >>> requisição bem-sucedida
        // .body(subscriptionTypes): Define o corpo da resposta como a lista de objetos
        // SubscriptionType que foi recuperada do banco de dados. O Spring converte esta lista para JSON automaticamente.
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypes);
    }
}
