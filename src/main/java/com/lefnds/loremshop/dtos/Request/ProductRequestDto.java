package com.lefnds.loremshop.dtos.Request;

import com.lefnds.loremshop.enums.Size;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    @NotBlank
    @Min(4)
    private String name;
    @NotNull
    private BigDecimal value;
    @NotNull
    private Integer amount;
    @NotBlank
    @Max(1)
    private Size size;
}
