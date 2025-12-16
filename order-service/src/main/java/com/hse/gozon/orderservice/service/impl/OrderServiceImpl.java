package com.hse.gozon.orderservice.service.impl;

import com.hse.gozon.dto.OrderCreateRequestDto;
import com.hse.gozon.dto.OrderDto;
import com.hse.gozon.orderservice.exception.NotFoundException;
import com.hse.gozon.orderservice.mapper.OrderAvroMapper;
import com.hse.gozon.orderservice.mapper.OutboxMapper;
import com.hse.gozon.orderservice.model.Order;
import com.hse.gozon.orderservice.model.OrderOutbox;
import com.hse.gozon.orderservice.producer.serializer.OrderCreatedEventSerializer;
import com.hse.gozon.orderservice.repository.OrderOutboxRepository;
import com.hse.gozon.orderservice.repository.OrderRepository;
import com.hse.gozon.orderservice.service.OrderService;
import com.hse.kafka.avro.event.OrderCreatedEventAvro;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.hse.gozon.orderservice.mapper.OrderMapper.toDto;
import static com.hse.gozon.orderservice.mapper.OrderMapper.toEntity;
import static com.hse.gozon.orderservice.mapper.OutboxMapper.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderOutboxRepository outboxRepository;
    private final OrderCreatedEventSerializer serializer;

    @Override
    @Transactional
    public OrderDto createOrder(OrderCreateRequestDto newOrder) {
        Order order = orderRepository.save(toEntity(newOrder));

        OrderCreatedEventAvro event = OrderAvroMapper.toAvro(order);

        OrderOutbox outbox = toOutbox(order, serializer.serialize(order.getId().toString(), event));
        outboxRepository.save(outbox);
        log.debug("заказ с id{} успешно сохранен", order.getId());
        return toDto(order);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public OrderDto findOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new NotFoundException("пользователь с id " + orderId + " не найден"));
        log.debug("пользователь с id{} успешно найден", order);
        return toDto(order);
    }

    @Override
    public List<OrderDto> findAllOrders() {
        return List.of();
    }
}
