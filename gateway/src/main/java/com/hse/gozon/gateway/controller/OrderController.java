package com.hse.gozon.gateway.controller;

import com.hse.gozon.dto.order.OrderCreateRequestDto;
import com.hse.gozon.dto.order.OrderDto;
import com.hse.gozon.gateway.client.OrderClient;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderClient orderClient;

    @PostMapping
    public OrderDto createOrder(@Validated @RequestBody OrderCreateRequestDto newOrder) {
        return orderClient.createOrder(newOrder);
    }

    @GetMapping("/{orderId}")
    public OrderDto findById(@PathVariable Long orderId) {
        return orderClient.findById(orderId);
    }

    @GetMapping("/accounts/{accountId}")
    public List<OrderDto> findOrdersByAccountId(@PathVariable Integer accountId) {
        return orderClient.findAllOrdersByAccountId(accountId);
    }
}
