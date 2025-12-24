package com.hse.gozon.dto.order;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderDto {
    private BigDecimal totalAmount;
    private Integer userId;
    private String status;
}
