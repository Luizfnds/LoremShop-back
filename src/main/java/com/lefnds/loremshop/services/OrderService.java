package com.lefnds.loremshop.services;

import com.lefnds.loremshop.enums.Status;
import com.lefnds.loremshop.model.Order;
import com.lefnds.loremshop.model.OrderItem;
import com.lefnds.loremshop.model.User;
import com.lefnds.loremshop.repositories.OrderItemRepository;
import com.lefnds.loremshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<Order> findAllUserOrders( User user ) {
        return orderRepository.findAllUserOrders(user);
    }

    public Order createOrder( User user,
                              List<UUID> orderItemIdList ) {
        List<OrderItem> orderItemList = orderItemRepository.findAllById( orderItemIdList );
        BigDecimal totalValue = orderItemList.stream()
                .map(e -> {
                    BigDecimal value = e.getProduct().getValue();
                    BigDecimal quantity = new BigDecimal( e.getQuantity() );
                    return value.multiply( quantity );
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = Order.builder()
                .user( user )
                .items( orderItemList )
                .status( Status.AUTHORIZING )
                .totalValue( totalValue )
                .build();
        return orderRepository.save( order );
    }

}
