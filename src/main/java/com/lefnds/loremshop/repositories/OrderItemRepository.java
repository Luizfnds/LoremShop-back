package com.lefnds.loremshop.repositories;

import com.lefnds.loremshop.model.Order;
import com.lefnds.loremshop.model.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {

    @Query("SELECT e FROM OrderItem e WHERE e.order = ?1")
    List<OrderItem> findOrderItemByOrderId(Order order);
}
