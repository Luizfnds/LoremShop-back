package com.lefnds.loremshop.services;

import com.lefnds.loremshop.dtos.Response.UserResponseDto;
import com.lefnds.loremshop.security.auth.TokenService;
import com.lefnds.loremshop.model.User;
import com.lefnds.loremshop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<User> findById( UUID id ) {
        return userRepository.findById( id );
    }

    @Transactional
    public User save( User user ) {
        return userRepository.save( user );
    }

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    public UserResponseDto userToUserResponseDto(User user ) {
        return  UserResponseDto.builder()
                .name( user.getName() )
                .surname( user.getSurname() )
                .email( user.getEmail() )
                .build();
    }

}
