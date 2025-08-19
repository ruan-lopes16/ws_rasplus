package com.client.ws.rasmooplus.service.impl;

import com.client.ws.rasmooplus.controller.SubscriptionTypeController;
import com.client.ws.rasmooplus.dto.SubscriptionTypeDto;
import com.client.ws.rasmooplus.exception.BadRequestException;
import com.client.ws.rasmooplus.exception.NotFoundException;
import com.client.ws.rasmooplus.mapper.SubscriptionTypeMapper;
import com.client.ws.rasmooplus.model.SubscriptionType;
import com.client.ws.rasmooplus.repository.SubscriptionTypeRepository;
import com.client.ws.rasmooplus.service.SubscriptionTypeService;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

    // como posso repetir isso em vários lugares posso criar um método final para cada um e depois só chamar o atributo estático
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";

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
        return getSubscriptionType(id).add(WebMvcLinkBuilder.linkTo(        // .add(links > recursos/endpoints q pode chamar depois de executar essa operação
                                                                            // WebMvcLinkBuilder.linkTo( > estou linkdando/navegando para...
            WebMvcLinkBuilder.methodOn(SubscriptionTypeController.class)    // apontando para a controller
                    .findById(id)).withSelfRel()                            // método da controller que queremos chamar + referencia(nome do objeto) > dizendo que está apontando para ela mesma(neste caso)

        ).add(WebMvcLinkBuilder.linkTo(

                WebMvcLinkBuilder.methodOn(SubscriptionTypeController.class)
                        .update(id, new SubscriptionTypeDto())).withRel(UPDATE) // withRel() > colocar o nome do recurso

        ) .add(WebMvcLinkBuilder.linkTo(

                WebMvcLinkBuilder.methodOn(SubscriptionTypeController.class)
                        .delete(id)).withRel(DELETE)

        );
    }

    @Override
    public SubscriptionType create(SubscriptionTypeDto dto) { // montando objeto com padrão builder > imutável

        // faz com que não consigamos atualizar em verbos POST
        if (Objects.nonNull(dto.getId())){  // criando situação para um BadRequest
            throw new BadRequestException("id must be null");
        }
        // foi isolado na classe SubscriptionTypeMapper
        return subscriptionTypeRepository.save(SubscriptionTypeMapper.fromDtoToEntity(dto));
    }

    @Override
    public SubscriptionType update(Long id, SubscriptionTypeDto dto) {
        // pegando trecho/info do código/método privado isolado
        getSubscriptionType(id);
        dto.setId(id);
        // foi isolado na classe SubscriptionTypeMapper
        return subscriptionTypeRepository.save(SubscriptionTypeMapper.fromDtoToEntity(dto));
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
