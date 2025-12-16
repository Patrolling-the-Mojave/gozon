package com.hse.gozon.orderservice.mapper;

import com.hse.gozon.orderservice.model.Order;
import com.hse.kafka.avro.event.OrderCreatedEventAvro;
import com.hse.kafka.avro.event.OrderStatus;

import java.time.Instant;
import java.time.ZoneOffset;

public class OrderAvroMapper {
    public static OrderCreatedEventAvro toAvro(Order order) {
        return OrderCreatedEventAvro.newBuilder()
                .setOrderId(order.getId())
                .setAmount(order.getTotalAmount().doubleValue())
                .setUserId(order.getUserId())
                .setCreatedAt(Instant.ofEpochSecond(order.getCreatedAt().toEpochSecond(ZoneOffset.UTC)))
                .setStatus(OrderStatus.valueOf(com.hse.gozon.orderservice.model.OrderStatus.PENDING.name()))
                .build();
    }
}
