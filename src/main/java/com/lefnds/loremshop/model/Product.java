package com.lefnds.loremshop.model;

import com.lefnds.loremshop.enums.Size;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID productId;
    @Column
    private String name;
    @Column
    private BigDecimal value;
    @Column
    private Integer amount;
    @Enumerated( EnumType.STRING )
    private Size size;

}
