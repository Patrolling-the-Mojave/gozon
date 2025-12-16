package com.hse.gozon.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class OrderCreateRequestDto {
    @NotNull
    @Min(0)
    private BigDecimal totalAmount;
    @NotNull
    private Integer userId;
}
