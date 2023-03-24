package com.lefnds.loremshop.security.auth;

import com.lefnds.loremshop.repositories.RoleRepository;
import com.lefnds.loremshop.security.auth.dtos.AuthenticationResponseDTO;
import com.lefnds.loremshop.security.auth.exceptions.EmailAlreadyInUseException;
import com.lefnds.loremshop.security.auth.dtos.LoginRequestDTO;
import com.lefnds.loremshop.security.auth.dtos.RegisterRequestDTO;
import com.lefnds.loremshop.enums.RoleName;
import com.lefnds.loremshop.model.User;
import com.lefnds.loremshop.repositories.UserRepository;
import com.lefnds.loremshop.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepository roleRepository;

    public AuthenticationResponseDTO registry( RegisterRequestDTO userDto ) {
        if( userService.findByEmail( userDto.getEmail() ).isPresent() ) {
            throw new EmailAlreadyInUseException();
        }
        User user = User.builder()
                .name( userDto.getName() )
                .surname( userDto.getSurname() )
                .email( userDto.getEmail() )
                .password( passwordEncoder.encode( userDto.getPassword() ) )
                .roles( Arrays.asList(roleRepository.findByRoleName(RoleName.ROLE_USER).get()) )
                .build();

        userService.save( user );
        String token = tokenService.generateToken( user );

        return AuthenticationResponseDTO.builder()
                .token( token )
                .build();
    }

    public AuthenticationResponseDTO authenticate(LoginRequestDTO loginDataDto ) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginDataDto.getEmail() , loginDataDto.getPassword()
        );

        authenticationManager.authenticate( usernamePasswordAuthenticationToken );
        User user = userService.findByEmail( loginDataDto.getEmail() )
                .orElseThrow(  );
        String token = tokenService.generateToken( user );

        return AuthenticationResponseDTO.builder()
                .token( token )
                .build();

    }
}
