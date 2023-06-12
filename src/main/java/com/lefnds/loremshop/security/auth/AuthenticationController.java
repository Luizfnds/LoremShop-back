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

        String token = authenticationService.authenticate(loginData);

        long tokenExp = tokenService.decodeToken(token).getExpiration().getTime();
        long tokenIat = tokenService.decodeToken(token).getIssuedAt().getTime();

        ResponseCookie cookie = ResponseCookie.from("token", token)
                //.httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path("/")
                .domain("https://lorem-shop-gules.vercel.app/user/data")
                .maxAge(Duration.ofMillis(tokenExp - tokenIat))
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.status( HttpStatus.OK ).body(TextResponseDTO.builder().message("sla").build());
    }

}
