package com.lefnds.loremshop.services;

import com.lefnds.loremshop.dtos.Request.AlterOrderItemDTO;
import com.lefnds.loremshop.dtos.Request.FilterRequestDto;
import com.lefnds.loremshop.dtos.Request.OrderItemDTO;
import com.lefnds.loremshop.model.Cart;
import com.lefnds.loremshop.model.OrderItem;
import com.lefnds.loremshop.model.Product;
import com.lefnds.loremshop.model.User;
import com.lefnds.loremshop.repositories.CartRepository;
import com.lefnds.loremshop.repositories.OrderItemRepository;
import com.lefnds.loremshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.Beans;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private UserService userService;

    public Cart findAllUserItemsOnCart(User user) {
        Cart cart = cartRepository.findUserCart(user);
        //List<OrderItem> items = cart.getItems();
        //items.forEach(item -> item.getProduct().setImage(convertByteaForBase64(item.getProduct().getImage())));
        return cart;
    }

    public Cart addItemOnCart(User user, OrderItemDTO orderItemDTO) {
        OrderItem orderItem = OrderItem.builder()
                .product(productRepository.findById(orderItemDTO.getProductId()).get())
                .quantity(orderItemDTO.getQuantity())
                .build();
        UUID uuid = orderItemRepository.save(orderItem).getId();
        Cart cart = user.getCart();
        cart.getItems().add(orderItemRepository.findById(uuid).get());
        cartRepository.save(cart);
        return cart;
    }

    public OrderItem alterItemOnCart(AlterOrderItemDTO orderItemDTO) {
        OrderItem orderItem = orderItemRepository.findById(orderItemDTO.getOrderItemId()).get();
        orderItem.setQuantity(orderItemDTO.getQuantity());
        return orderItemRepository.save(orderItem);
    }

    public void deleteItemOnCart(User user, UUID orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).get();
        Cart cart = user.getCart();
        cart.getItems().remove(orderItem);
        cartRepository.save(cart);
        //orderItemRepository.delete(orderItem);
    }

//    private byte[] convertByteaForBase64(byte[] bytea) {
//        return Base64.getDecoder().decode(bytea);
//    }

}
