package com.lefnds.loremshop.repositories;

import com.lefnds.loremshop.model.Order;
import com.lefnds.loremshop.model.OrderItem;
import com.lefnds.loremshop.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query("SELECT e FROM Order e WHERE e.user = ?1")
    List<Order> findAllUserOrders(User user);

}
