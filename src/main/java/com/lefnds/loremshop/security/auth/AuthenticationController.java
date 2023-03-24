package com.lefnds.loremshop.security.auth;

import com.lefnds.loremshop.security.auth.dtos.AuthenticationResponseDTO;
import com.lefnds.loremshop.security.auth.dtos.LoginRequestDTO;
import com.lefnds.loremshop.security.auth.dtos.RegisterRequestDTO;
import com.lefnds.loremshop.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @PostMapping( "/register" )
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody @Valid RegisterRequestDTO userDto ) {
        return ResponseEntity.status( HttpStatus.OK ).body( authenticationService.registry( userDto ) );
    }

    @PostMapping( "/authenticate" )
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody @Valid LoginRequestDTO loginData ){
        return ResponseEntity.status( HttpStatus.OK ).body( authenticationService.authenticate( loginData ) );
    }

}
