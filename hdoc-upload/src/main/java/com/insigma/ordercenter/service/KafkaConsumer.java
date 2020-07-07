package com.insigma.ordercenter.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author youwk
 * @ClassName KafkaConsumer
 * @description TODO
 * @date 2020/7/7 15:26
 * @Version 1.0
 */
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics ="hdoc-dev",groupId = "dev1")
    public void listen (ConsumerRecord<?,?> record) {
        log.error("topic is {}, offset is {}, value is {} partition is {} \n",
                record.topic(), record.offset(), record.value(),record.partition());
    }

    @KafkaListener(topics ="hdoc-dev",groupId = "dev2")
    public void listen1 (ConsumerRecord<?,?> record) {
        log.error("topic is {}, offset is {}, value is {} partition is {} \n",
                record.topic(), record.offset(), record.value(),record.partition());
    }
    @KafkaListener(topics ="hdoc-dev",groupId = "dev1")
    public void listen2 (ConsumerRecord<?,?> record) {
        log.error("topic is {}, offset is {}, value is {} partition is {} \n",
                record.topic(), record.offset(), record.value(),record.partition());
    }
}
