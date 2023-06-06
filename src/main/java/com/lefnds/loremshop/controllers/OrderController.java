package com.lefnds.loremshop.controllers;

import com.lefnds.loremshop.model.Order;
import com.lefnds.loremshop.model.OrderItem;
import com.lefnds.loremshop.model.User;
import com.lefnds.loremshop.repositories.OrderItemRepository;
import com.lefnds.loremshop.security.auth.TokenService;
import com.lefnds.loremshop.services.CartService;
import com.lefnds.loremshop.services.OrderService;
import com.lefnds.loremshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private CartService cartService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping
    public ResponseEntity<List<Order>> findAllUserOrders( @RequestHeader( "Authorization" ) String token ) {
        User user = userService.findByEmail( tokenService.getSubject( token ) ).get();
        return ResponseEntity.status(HttpStatus.OK).body( orderService.findAllUserOrders(user) );
    }

    @PostMapping
    public ResponseEntity<Order> createOrder( @RequestHeader( "Authorization" ) String token,
                                              @RequestBody List<UUID> orderItemIdList ) {
        User user = userService.findByEmail( tokenService.getSubject( token ) ).get();
        Order order = orderService.createOrder( user, orderItemIdList );
        orderItemIdList.forEach(e -> {
            OrderItem orderItem = orderItemRepository.findById(e).get();
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        });
        orderItemIdList.forEach(e -> cartService.deleteItemOnCart(user, e));
        return ResponseEntity.status(HttpStatus.OK).body( order );
    }

}
