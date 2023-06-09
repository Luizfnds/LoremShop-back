package com.lefnds.loremshop.security.auth;

import com.lefnds.loremshop.dtos.Response.TextResponseDTO;
import com.lefnds.loremshop.security.auth.dtos.AuthenticationResponseDTO;
import com.lefnds.loremshop.security.auth.dtos.LoginRequestDTO;
import com.lefnds.loremshop.security.auth.dtos.RegisterRequestDTO;
import com.lefnds.loremshop.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Date;

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
    public ResponseEntity<Object> authenticate(@RequestBody @Valid LoginRequestDTO loginData,
                                               HttpServletResponse response){
        if(userService.findByEmail(loginData.getEmail()).isEmpty()) {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "Email not registered" );
        }
        
        return ResponseEntity.status( HttpStatus.OK ).body( authenticationService.authenticate(loginData) );
    }

}
