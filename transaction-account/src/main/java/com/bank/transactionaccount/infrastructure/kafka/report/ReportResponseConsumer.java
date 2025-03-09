package com.bank.transactionaccount.infrastructure.kafka.report;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ReportResponseConsumer {
    @KafkaListener(topics = "report-response-topic", groupId = "transaction-account-consumer")
    public void consumeReportResponse(String message) {
        System.out.println("Received report data: " + message);
        // TODO: PROCESAR GENERAR REPORTE
    }
}
