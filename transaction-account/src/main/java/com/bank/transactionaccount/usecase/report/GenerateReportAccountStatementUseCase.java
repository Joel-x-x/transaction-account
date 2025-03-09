package com.bank.transactionaccount.usecase.report;

import com.bank.transactionaccount.entity.account.exception.AccountNotFoundException;
import com.bank.transactionaccount.entity.account.gateway.AccountGateway;
import com.bank.transactionaccount.entity.account.model.Account;
import com.bank.transactionaccount.entity.transaction.gateway.TransactionGateway;
import com.bank.transactionaccount.infrastructure.kafka.report.ReportRequestProducer;
import com.bank.transactionaccount.infrastructure.kafka.report.ReportResponseConsumer;
import com.bank.transactionaccount.infrastructure.kafka.report.dto.CustomerPublicData;
import com.bank.transactionaccount.usecase.report.dto.IReportAccountStatementPublicData;
import com.bank.transactionaccount.usecase.report.dto.impl.ReportAccountStatementPublicData;
import com.bank.transactionaccount.usecase.report.dto.impl.ReportAccountStatementRegistrationData;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class GenerateReportAccountStatementUseCase {

    private final AccountGateway accountGateway;
    private final TransactionGateway transactionGateway;
    private final ReportRequestProducer reportRequestProducer;
    private final ReportResponseConsumer reportResponseConsumer;

    public GenerateReportAccountStatementUseCase(AccountGateway accountGateway,
                                                 TransactionGateway transactionGateway,
                                                 ReportRequestProducer reportRequestProducer,
                                                 ReportResponseConsumer reportResponseConsumer
    ) {
        this.accountGateway = accountGateway;
        this.transactionGateway = transactionGateway;
        this.reportRequestProducer = reportRequestProducer;
        this.reportResponseConsumer = reportResponseConsumer;
    }

    public IReportAccountStatementPublicData execute(String customerId, LocalDate start, LocalDate end) throws AccountNotFoundException {
        ReportAccountStatementRegistrationData dados = new ReportAccountStatementRegistrationData(customerId, start, end);

        // Kafka
        reportRequestProducer.requestCustomerData(customerId);
        CustomerPublicData customer = reportResponseConsumer.getCustomerData();

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
                customer.id(),
                customer.name(),
                customer.identification()
        );

        return new ReportAccountStatementPublicData(customerReport, accountReports);
    }


}
