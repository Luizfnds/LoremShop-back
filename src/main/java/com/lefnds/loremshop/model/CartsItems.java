//package com.lefnds.loremshop.model;
//
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.List;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//@Table(name = "tb_carts_items")
//public class CartsItems {
//
////    @ManyToMany(fetch = FetchType.EAGER)
////    @JoinTable(name = "tb_carts_items",
////            joinColumns = @JoinColumn(name = "cart_id"),
////            inverseJoinColumns = @JoinColumn(name = "order_item_id"))
////    private List<OrderItem> items;
//
////    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "items")
////    @JsonIgnore
////    //@OnDelete(action = OnDeleteAction.CASCADE)
////    private List<Cart> cart;
//
//    @ManyToOne
//    @JoinColumn(name = "cart_id")
//    private Cart cart;
//    @ManyToOne
//    @JoinColumn(name = "order_item_id")
//    private OrderItem orderItem;
//}
