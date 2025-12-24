package com.hse.gozon.dto.order;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class OrderCreateRequestDto {
    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal totalAmount;
    @NotNull
    private Integer userId;
}
