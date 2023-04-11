package com.lefnds.loremshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lefnds.loremshop.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> items;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Status status;

}
