package com.bank.transactionaccount.usecase.report.dto.impl;

import com.bank.transactionaccount.usecase.report.dto.IReportAccountStatementPublicData;

import java.math.BigDecimal;
import java.util.List;

public record ReportAccountStatementPublicData(
        CustomerReportPublicData customer,
        List<IReportAccountStatementPublicData.IAccountReportPublicData> accounts
) implements IReportAccountStatementPublicData {

    public record CustomerReportPublicData(
            String id,
            String name,
            String identification
    ) implements IReportAccountStatementPublicData.ICostumerReportPublicData {}

    public record AccountReportPublicData(
            String accountNumber,
            String alias,
            String accountType,
            BigDecimal initialBalance,
            List<IReportAccountStatementPublicData.ITransactionReportPublicData> transactions
    ) implements IReportAccountStatementPublicData.IAccountReportPublicData {

        public record TransactionReportPublicData(
                String transactionType,
                BigDecimal amount,
                BigDecimal balanceAfterTransaction,
                String date
        ) implements IReportAccountStatementPublicData.ITransactionReportPublicData {}
    }
}
