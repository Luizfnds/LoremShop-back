package com.lefnds.loremshop.dtos.Request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlterOrderItemDTO {

    @NotNull
    private int quantity;
    @NotNull
    private UUID orderItemId;

}
