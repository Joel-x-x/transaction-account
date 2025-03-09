package com.bank.transactionaccount.infrastructure.kafka.report;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReportRequestProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ReportRequestProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendReportRequest(String customerId) {
        String reportRequestMessage = "Requesting report for customer: " + customerId;
        kafkaTemplate.send("report-request-topic", customerId, reportRequestMessage);
    }
}
