package com.lefnds.loremshop.services;

import com.lefnds.loremshop.model.Order;
import com.lefnds.loremshop.model.OrderItem;
import com.lefnds.loremshop.repositories.OrderItemRepository;
import com.lefnds.loremshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<OrderItem> findOrderItemByOrderId(Order order) {
        return orderItemRepository.findOrderItemByOrderId(order);
    }
}
