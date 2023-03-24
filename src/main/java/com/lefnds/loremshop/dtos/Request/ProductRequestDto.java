package com.lefnds.loremshop.dtos.Request;

import com.lefnds.loremshop.enums.ProductGender;
import com.lefnds.loremshop.enums.ProductSize;
import com.lefnds.loremshop.enums.ProductType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @NotNull
    private ProductType type;
    @NotBlank
    @NotNull
    private ProductSize size;
    @NotBlank
    @NotNull
    private ProductGender gender;
    @NotBlank
    @NotNull
    @Max(1)
    private ProductSize productSize;
}
