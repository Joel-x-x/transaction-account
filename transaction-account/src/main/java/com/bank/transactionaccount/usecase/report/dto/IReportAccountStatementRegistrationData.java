package com.bank.transactionaccount.usecase.report.dto;

import java.time.LocalDate;
import java.util.UUID;

public interface IReportAccountStatementRegistrationData {
    String customerId();
    LocalDate start();
    LocalDate end();
}
