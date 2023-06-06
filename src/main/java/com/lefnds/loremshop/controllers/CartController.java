package com.lefnds.loremshop.controllers;

import com.lefnds.loremshop.dtos.Request.AlterOrderItemDTO;
import com.lefnds.loremshop.dtos.Request.OrderItemDTO;
import com.lefnds.loremshop.dtos.Response.TextResponseDTO;
import com.lefnds.loremshop.model.*;
import com.lefnds.loremshop.repositories.OrderItemRepository;
import com.lefnds.loremshop.security.auth.TokenService;
import com.lefnds.loremshop.services.CartService;
import com.lefnds.loremshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping
    public ResponseEntity<Cart> findAllUserItemsOnCart( @RequestHeader( "Authorization" ) String token ) {
        User user = userService.findByEmail( tokenService.getSubject( token ) ).get();
        return ResponseEntity.status(HttpStatus.OK).body( cartService.findAllUserItemsOnCart(user) );
    }

    @PostMapping
    public ResponseEntity<Cart> addItemOnCart( @RequestHeader( "Authorization" ) String token,
                                               @RequestBody OrderItemDTO orderItemDTO ) {
        User user = userService.findByEmail( tokenService.getSubject( token ) ).get();
        return ResponseEntity.status(HttpStatus.OK).body( cartService.addItemOnCart(user, orderItemDTO) );
    }

    @PutMapping
    public ResponseEntity<OrderItem> alterItemOnCart( @RequestBody AlterOrderItemDTO orderItemDTO ) {
        return ResponseEntity.status( HttpStatus.OK ).body( cartService.alterItemOnCart( orderItemDTO ) );
    }

    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<Object> deleteItemOnCart( @RequestHeader( "Authorization" ) String token,
                                                    @PathVariable UUID orderItemId ) {
        User user = userService.findByEmail( tokenService.getSubject( token ) ).get();
        cartService.deleteItemOnCart(user, orderItemId);
        orderItemRepository.delete(orderItemRepository.findById(orderItemId).get());
        return ResponseEntity.status( HttpStatus.OK ).body( TextResponseDTO.builder().message("Product deleted successfully").build() );
    }
}
