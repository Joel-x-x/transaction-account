package com.bank.transactionaccount.infrastructure.kafka.report.dto;

public record CustomerPublicData(
        String id,
        String name,
        String genre,
        String birthDate,
        String identification,
        String address,
        String phone
) {}
