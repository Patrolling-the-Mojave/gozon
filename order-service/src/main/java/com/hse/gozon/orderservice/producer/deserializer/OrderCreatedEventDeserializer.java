package com.hse.gozon.orderservice.producer.deserializer;

import com.hse.kafka.avro.deserializer.BaseAvroDeserializer;
import com.hse.kafka.avro.event.OrderCreatedEventAvro;

public class OrderCreatedEventDeserializer extends BaseAvroDeserializer<OrderCreatedEventAvro> {
    public OrderCreatedEventDeserializer() {
        super(OrderCreatedEventAvro.getClassSchema());
    }
}
