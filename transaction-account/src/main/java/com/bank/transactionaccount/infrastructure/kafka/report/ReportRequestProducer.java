package com.bank.transactionaccount.infrastructure.kafka.report;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReportRequestProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ReportRequestProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void requestCustomerData(String customerId) {
        kafkaTemplate.send("customer-data-request", customerId);
    }
}
