package com.bank.transactionaccount.infrastructure.kafka.report;

import com.bank.transactionaccount.infrastructure.kafka.report.dto.CustomerPublicData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ReportResponseConsumer {

    private CustomerPublicData customerPublicData;

    @KafkaListener(topics = "customer-data-response", groupId = "bank-group")
    public void listenCustomerResponse(String message) {
        try {
            // Deserializar el mensaje JSON en un objeto CustomerPublicData
            this.customerPublicData = new ObjectMapper().readValue(message, CustomerPublicData.class);
        } catch (Exception e) {
            e.printStackTrace();  // Manejo de excepciones en caso de error de deserialización
        }
    }

    public CustomerPublicData getCustomerData() {
        return this.customerPublicData;
    }

}
