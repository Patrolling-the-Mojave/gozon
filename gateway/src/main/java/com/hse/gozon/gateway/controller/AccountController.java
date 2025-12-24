package com.hse.gozon.gateway.controller;

import com.hse.gozon.dto.account.AccountCreateDto;
import com.hse.gozon.dto.account.AccountDto;
import com.hse.gozon.dto.account.DepDto;
import com.hse.gozon.gateway.client.AccountClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountClient accountClient;

    @PostMapping
    public AccountDto createAccount(@Validated @RequestBody AccountCreateDto newAccount) {
        return accountClient.createAccount(newAccount);
    }

    @GetMapping("/{accountId}")
    public AccountDto findById(@PathVariable Integer accountId){
        return accountClient.findById(accountId);
    }

    @PatchMapping("/deposit")
    public AccountDto deposit(@Validated @RequestBody DepDto depDto){
        return accountClient.deposit(depDto);
    }
}
