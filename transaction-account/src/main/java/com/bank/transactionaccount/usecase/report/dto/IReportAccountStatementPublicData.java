package com.bank.transactionaccount.usecase.report.dto;

import java.math.BigDecimal;
import java.util.List;

public interface IReportAccountStatementPublicData {

    ICostumerReportPublicData customer();

    List<IAccountReportPublicData> accounts();

    interface ICostumerReportPublicData {
        String id();
        String name();
        String identification();
    }

    interface IAccountReportPublicData {
        String accountNumber();
        String alias();
        String accountType();
        BigDecimal initialBalance();
        List<ITransactionReportPublicData> transactions();
    }

    interface ITransactionReportPublicData {
        String transactionType();
        BigDecimal amount();
        BigDecimal balanceAfterTransaction();
        String date();
    }
}
