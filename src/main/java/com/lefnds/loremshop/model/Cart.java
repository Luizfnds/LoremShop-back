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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_carts_items",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "order_item_id"))
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<OrderItem> items;
//    @OneToMany(mappedBy = "cart")
//    private List<OrderItem> items = new ArrayList<>();
}
