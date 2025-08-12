package com.client.ws.rasmooplus.controller;

import com.client.ws.rasmooplus.dto.SubscriptionTypeDto;
import com.client.ws.rasmooplus.model.SubscriptionType;
import com.client.ws.rasmooplus.service.SubscriptionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

// Dizendo que é um controller > Spring irá gerenciar esta classe como um componente
@RestController
@RequestMapping("/subscriptions-type") // mapeia a URL base para as requisições
public class SubscriptionTypeController { // qualquer método aqui só será executado caso tenha a URL /subscriptions-type

    // Injeção de dependência. O Spring irá criar uma instância de SubscriptionTypeRepository
    // e a "injetar" nesta variável, tornando-a disponível para uso.
    @Autowired
    private SubscriptionTypeService subscriptionTypeService; // BANCO

    @GetMapping()   // dizendo que é uma requisição do tipo GET
    // O método retorna um 'ResponseEntity', permitindo um controle total da resposta HTTP.
    // O tipo de retorno é uma lista de objetos 'SubscriptionType'
    public ResponseEntity<List<SubscriptionType>> findAll(){
        // O método 'findAll' do repositório busca todos os registros na tabela 'subscriptions_type'.
        List<SubscriptionType> subscriptionTypes = subscriptionTypeService.findAll();

        // Constrói e retorna a resposta HTTP.
        // ResponseEntity.status(HttpStatus.OK): Define o código de status HTTP como 200 (OK) >>> requisição bem-sucedida
        // .body(subscriptionTypes): Define o corpo da resposta como a lista de objetos
        // SubscriptionType que foi recuperada do banco de dados. O Spring converte esta lista para JSON automaticamente.
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypes);
    }

    @GetMapping("/{id}") // método para responder as requisições
    public ResponseEntity<SubscriptionType> findById(@PathVariable("id") Long id) { // PathVariable > identifica um trecho da URL(variavel de caminho)

/*  antes para fazer a validação do id passado na url
        SubscriptionType subscriptionType = subscriptionTypeService.findById(id);
        if (Objects.nonNull(subscriptionType)) {    // Verifica se o objeto retornado do serviço não é nulo
            return ResponseEntity.status(HttpStatus.OK)
                    .body(subscriptionType);
        }
 */

/*  agora com tratamento de exceção > try-catch
        try {
            return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeService.findById(id));

        } catch (NotFoundException notFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);

        }
*/
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeService.findById(id));

    }

    // criando coisas no banco de dados
    @PostMapping
    public ResponseEntity<SubscriptionType> create(@RequestBody SubscriptionTypeDto dto) { // passamos um corpo na requisição (JSON)
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionTypeService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionType> create(@PathVariable("id") Long id, @RequestBody SubscriptionTypeDto dto) { // passamos um corpo na requisição (JSON)
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeService.update(id, dto)); // me retorna um ok, e eu passo o id + dto
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){ // Void > irá apenas executar algo >>> não preciso passar minha RequestBody, pois irei fazer uma exclusão e é void
        subscriptionTypeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // em sucesso, me retorna um 204 nulo
    }

}
