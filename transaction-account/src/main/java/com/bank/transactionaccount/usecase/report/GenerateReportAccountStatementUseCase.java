package com.bank.transactionaccount.usecase.report;

import com.bank.transactionaccount.entity.account.exception.AccountNotFoundException;
import com.bank.transactionaccount.entity.account.gateway.AccountGateway;
import com.bank.transactionaccount.entity.account.model.Account;
import com.bank.transactionaccount.entity.transaction.gateway.TransactionGateway;
import com.bank.transactionaccount.usecase.report.dto.IReportAccountStatementPublicData;
import com.bank.transactionaccount.usecase.report.dto.impl.ReportAccountStatementPublicData;
import com.bank.transactionaccount.usecase.report.dto.impl.ReportAccountStatementRegistrationData;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class GenerateReportAccountStatementUseCase {

    private final AccountGateway accountGateway;
    private final TransactionGateway transactionGateway;

    public GenerateReportAccountStatementUseCase(AccountGateway accountGateway, TransactionGateway transactionGateway) {
        this.accountGateway = accountGateway;
        this.transactionGateway = transactionGateway;
    }

    public IReportAccountStatementPublicData execute(String customerId, LocalDate start, LocalDate end) throws AccountNotFoundException {
        ReportAccountStatementRegistrationData dados = new ReportAccountStatementRegistrationData(customerId, start, end);

        List<Account> accounts = accountGateway.findAllByCustomerId(dados.customerId());

        List<IReportAccountStatementPublicData.IAccountReportPublicData> accountReports = accounts.stream()
                .map(account -> {
                    List<IReportAccountStatementPublicData.ITransactionReportPublicData> transactionReports =
                            transactionGateway.findTransactionsByAccountAndDateRange(account.getId(), dados.start(), dados.end())
                                    .stream()
                                    .map(transaction -> new ReportAccountStatementPublicData.AccountReportPublicData.TransactionReportPublicData(
                                            transaction.getTransactionType().getName(),
                                            transaction.getAmount(),
                                            transaction.getBalance(),
                                            transaction.getDate().toString()
                                    ))
                                    .collect(Collectors.toList());

                    return new ReportAccountStatementPublicData.AccountReportPublicData(
                            account.getAccountNumber(),
                            account.getAlias(),
                            account.getAccountType().getName(),
                            account.getInitialBalance(),
                            transactionReports
                    );
                })
                .collect(Collectors.toList());

        ReportAccountStatementPublicData.CustomerReportPublicData customerReport = new ReportAccountStatementPublicData.CustomerReportPublicData(
                dados.customerId().toString(),
                "dados.customerName()",
                "dados.customerIdentification()"
        );

        return new ReportAccountStatementPublicData(customerReport, accountReports);
    }


}
