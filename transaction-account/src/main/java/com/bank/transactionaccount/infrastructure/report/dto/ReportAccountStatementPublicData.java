package com.bank.transactionaccount.infrastructure.report.dto;

import com.bank.transactionaccount.usecase.report.dto.IReportAccountStatementPublicData;

import java.math.BigDecimal;
import java.util.List;

public record ReportAccountStatementPublicData(
        CustomerReportPublicData customer,
        List<IAccountReportPublicData> accounts
) implements IReportAccountStatementPublicData {

    public record CustomerReportPublicData(
            String id,
            String name,
            String identification
    ) implements ICostumerReportPublicData {}

    public record AccountReportPublicData(
            String accountNumber,
            String alias,
            String accountType,
            BigDecimal initialBalance,
            List<ITransactionReportPublicData> transactions
    ) implements IAccountReportPublicData {

        public record TransactionReportPublicData(
                String transactionType,
                BigDecimal amount,
                BigDecimal balanceAfterTransaction,
                String date
        ) implements ITransactionReportPublicData {}
    }
}