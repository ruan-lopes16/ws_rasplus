package com.client.ws.rasmooplus.service.impl;

import com.client.ws.rasmooplus.dto.UserDto;
import com.client.ws.rasmooplus.exception.BadRequestException;
import com.client.ws.rasmooplus.exception.NotFoundException;
import com.client.ws.rasmooplus.mapper.UserMapper;
import com.client.ws.rasmooplus.model.User;
import com.client.ws.rasmooplus.model.UserType;
import com.client.ws.rasmooplus.repository.UserRepository;
import com.client.ws.rasmooplus.repository.UserTypeRepository;
import com.client.ws.rasmooplus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    // chamando repositories > para injeção
    private final UserRepository userRepository;

    private final UserTypeRepository userTypeRepository;

    // injeção via construtor
    UserServiceImpl(UserRepository userRepository, UserTypeRepository userTypeRepository){
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public User create(UserDto dto) {

        // verificando se o dto passou id nulo
        if(Objects.nonNull(dto.getId())) {
          throw new BadRequestException("id must be null");

        }

        // consultando se usertype existe > Java 10+
        // sem usar o optional
        var userTypeOpt = userTypeRepository.findById(dto.getUserTypeId());

        if (userTypeOpt.isEmpty()){
            throw new NotFoundException("userTypeId not found");

        }

        // atribuindo usertype a entidade
        UserType userType = userTypeOpt.get();
        User user = UserMapper.fromDtoToEntity(dto, userType, null);

        return userRepository.save(user);
    }
}
