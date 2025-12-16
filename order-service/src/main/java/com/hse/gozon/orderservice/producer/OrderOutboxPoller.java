package com.hse.gozon.orderservice.producer;

import com.hse.gozon.orderservice.exception.OrderServiceException;
import com.hse.gozon.orderservice.model.OrderOutbox;
import com.hse.gozon.orderservice.producer.deserializer.OrderCreatedEventDeserializer;
import com.hse.gozon.orderservice.repository.OrderOutboxRepository;
import com.hse.kafka.avro.event.OrderCreatedEventAvro;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderOutboxPoller {
    private final KafkaProducer<String, OrderCreatedEventAvro> orderKafkaProducer;
    private final OrderOutboxRepository outboxRepository;
    private final OrderCreatedEventDeserializer deserializer;

    @Value("${gozon.orders.v1}")
    private String orderTopic;

    @Scheduled(fixedDelay = 2000)
    public void publish() {
        List<OrderOutbox> unprocessedOrders = outboxRepository.findUnprocessed();
        try {
            for (OrderOutbox unprocessedOrder : unprocessedOrders) {
                OrderCreatedEventAvro event = deserializer.deserialize(unprocessedOrder.getOrderId().toString(), unprocessedOrder.getPayload());
                ProducerRecord<String, OrderCreatedEventAvro> record = new ProducerRecord<>(String.valueOf(event.getOrderId()), event);
                orderKafkaProducer.send(record);
            }
        } catch (Exception exception) {
            throw new OrderServiceException("произошла ошибка при отправке данных в топик " + orderTopic, exception);
        }
    }
}
