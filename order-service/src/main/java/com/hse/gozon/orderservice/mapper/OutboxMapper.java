package com.hse.gozon.orderservice.mapper;

import com.hse.gozon.orderservice.model.Order;
import com.hse.gozon.orderservice.model.OrderOutbox;

import java.util.Arrays;
import java.util.UUID;

public class OutboxMapper {

    public static OrderOutbox toOutbox(Order order, byte[] payload){
        return OrderOutbox.builder()
                .orderId(order.getId())
                .eventId(UUID.randomUUID())
                .payload(payload)
                .createdAt(order.getCreatedAt())
                .build();
    }
}
