package com.lefnds.loremshop.controllers;

import com.lefnds.loremshop.dtos.Request.UserRequestDto;
import com.lefnds.loremshop.dtos.Response.UserResponseDto;
import com.lefnds.loremshop.enums.RoleName;
import com.lefnds.loremshop.model.Role;
import com.lefnds.loremshop.model.User;
import com.lefnds.loremshop.repositories.RoleRepository;
import com.lefnds.loremshop.repositories.UserRepository;
import com.lefnds.loremshop.security.auth.TokenService;
import com.lefnds.loremshop.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<Page<UserResponseDto>> findAllUser(@PageableDefault(page = 0, size = 10,sort = "userId", direction = Sort.Direction.ASC) Pageable pageable) {
        List<UserResponseDto> list = userService.findAll(pageable).stream()
                .map(UserResponseDto::userToUserResponseDto)
                .toList();
        Page<UserResponseDto> page = new PageImpl<>(list);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity< UserResponseDto > findUser( @RequestHeader( "Authorization" ) String token ) {
        User user = userService.findByEmail( tokenService.getSubject( token ) ).get();
        UserResponseDto userResponseDto = userService.userToUserResponseDto( user );

        return ResponseEntity.status(HttpStatus.OK).body( userResponseDto );
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity< UserResponseDto > alterUserById(@RequestHeader( "Authorization" ) String token,
                                                           @RequestBody @Valid UserRequestDto userRequestDTO ) {
        User user = userService.findByEmail( tokenService.getSubject( token ) )
                .orElseThrow();

        user.setName( userRequestDTO.getName() );
        user.setSurname( userRequestDTO.getSurname() );

        userService.save( user );
        UserResponseDto userResponseDto = userService.userToUserResponseDto( user );

        return ResponseEntity.status(HttpStatus.OK).body( userResponseDto );
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestHeader("Authorization") String token) {
        User user = userService.findByEmail(tokenService.getSubject(token)).get();
        userService.delete(user);

        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }

}
