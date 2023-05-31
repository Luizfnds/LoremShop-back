package com.lefnds.loremshop.dtos.Request;

import com.lefnds.loremshop.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    @NotNull
    private int quantity;
    @NotNull
    private UUID productId;

}
