package com.client.ws.rasmooplus.service.impl;

import com.client.ws.rasmooplus.dto.SubscriptionTypeDto;
import com.client.ws.rasmooplus.exception.BadRequestException;
import com.client.ws.rasmooplus.exception.NotFoundException;
import com.client.ws.rasmooplus.model.SubscriptionType;
import com.client.ws.rasmooplus.repository.SubscriptionTypeRepository;
import com.client.ws.rasmooplus.service.SubscriptionTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

    private final SubscriptionTypeRepository subscriptionTypeRepository; // importando Repository
    // injeção ⬇️
    public SubscriptionTypeServiceImpl(SubscriptionTypeRepository subscriptionTypeRepository) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }

    @Override
    public List<SubscriptionType> findAll() {
        return subscriptionTypeRepository.findAll();
    }

    @Override
    public SubscriptionType findById(Long id) {

        // pegando trecho/info do código/método privado isolado
        return getSubscriptionType(id);
    }

    @Override
    public SubscriptionType create(SubscriptionTypeDto dto) { // montando objeto com padrão builder > imutável

        // faz com que não consigamos atualizar em verbos POST
        if (Objects.nonNull(dto.getId())){  // criando situação para um BadRequest
            throw new BadRequestException("id must be null");
        }
        return subscriptionTypeRepository.save(SubscriptionType.builder()
                        .id(dto.getId())
                        .name(dto.getName())
                        .accessMonth(dto.getAccessMonth())
                        .price(dto.getPrice())
                        .productKey(dto.getProductKey())
                .build());
    }

    @Override
    public SubscriptionType update(Long id, SubscriptionTypeDto dto) {
        // pegando trecho/info do código/método privado isolado
        getSubscriptionType(id);

        return subscriptionTypeRepository.save(SubscriptionType.builder()
                .id(id)     // colocando id aqui, pois não preciso acessá-lo no dto, neste caso ele é um parametro
                .name(dto.getName())
                .accessMonth(dto.getAccessMonth())
                .price(dto.getPrice())
                .productKey(dto.getProductKey())
                .build());
    }

    @Override
    public void delete(Long id) {
        getSubscriptionType(id);    // verificando se o id existe(método isolado)

        subscriptionTypeRepository.deleteById(id); // em caso de OK > faz o Delete

    }

    // isolando método, para ser reaproveitado por outros métodos
    private SubscriptionType getSubscriptionType(Long id) {
        Optional<SubscriptionType> optionalSubscriptionType = subscriptionTypeRepository.findById(id); // Optional para evitar erros caso o item não exista (pode ser que encontre, mas pode ser que não)
        if (optionalSubscriptionType.isEmpty()){
            throw new NotFoundException("SubscriptionType not found.");
        }
        return optionalSubscriptionType.get();
    }
}
