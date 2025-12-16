package com.hse.gozon.orderservice.producer.config;

import com.hse.gozon.orderservice.producer.deserializer.OrderCreatedEventDeserializer;
import com.hse.gozon.orderservice.producer.serializer.OrderCreatedEventSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SerializationConfig {

    @Bean
    public OrderCreatedEventSerializer serializer() {
        return new OrderCreatedEventSerializer();
    }

    @Bean
    public OrderCreatedEventDeserializer deserializer() {
        return new OrderCreatedEventDeserializer();
    }
}

