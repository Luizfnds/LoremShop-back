package com.lefnds.loremshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
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
    //@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private List<Cart> cart;
//    @OneToMany(mappedBy = "orderItem")
//    private List<OrderItem> items = new ArrayList<>();
    @Column
    private Integer quantity;

}
