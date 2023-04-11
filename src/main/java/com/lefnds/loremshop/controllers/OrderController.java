package com.lefnds.loremshop.controllers;

import com.lefnds.loremshop.model.Order;
import com.lefnds.loremshop.model.OrderItem;
import com.lefnds.loremshop.model.User;
import com.lefnds.loremshop.security.auth.TokenService;
import com.lefnds.loremshop.services.OrderService;
import com.lefnds.loremshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<List<Order>> findAllUserOrders( @RequestHeader( "Authorization" ) String token ) {
        User user = userService.findByEmail( tokenService.getSubject( token ) ).get();
        return ResponseEntity.status(HttpStatus.OK).body( orderService.findAllUserOrders(user) );
    }

}
