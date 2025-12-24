package com.hse.gozon.dto.account;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountDto {
    private String name;
    private String email;
    private BigDecimal balance;
}
