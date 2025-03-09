package com.bank.transactionaccount.usecase.report.dto.impl;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record ReportAccountStatementRegistrationData(
        @NotNull
        String customerId,
        @NotNull
        LocalDate start,
        @NotNull
        LocalDate end
) {
}
