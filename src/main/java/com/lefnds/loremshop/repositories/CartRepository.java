package com.lefnds.loremshop.repositories;

import com.lefnds.loremshop.model.Cart;
import com.lefnds.loremshop.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, UUID> {

    @Query("SELECT e FROM Cart e WHERE e.user = ?1")
    Cart findUserCart(User user);

}
