package com.lefnds.loremshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="order_id")
    @JsonIgnore
    private Order order;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id", nullable=false)
    private Product product;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "items")
    @JsonIgnore
    private List<Cart> cart;
    @Column
    private Integer quantity;

}
