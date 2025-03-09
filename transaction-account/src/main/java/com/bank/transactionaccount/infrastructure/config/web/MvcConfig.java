package com.bank.transactionaccount.infrastructure.config.web;

import com.bank.transactionaccount.entity.account.gateway.AccountGateway;
import com.bank.transactionaccount.entity.accounttype.gateway.AccountTypeGateway;
import com.bank.transactionaccount.entity.transaction.gateway.TransactionGateway;
import com.bank.transactionaccount.entity.transactiontype.gateway.TransactionTypeGateway;
import com.bank.transactionaccount.infrastructure.account.gateway.AccountDatabaseGateway;
import com.bank.transactionaccount.infrastructure.accounttype.gateway.AccountTypeDatabaseGateway;
import com.bank.transactionaccount.infrastructure.config.db.repository.AccountRepository;
import com.bank.transactionaccount.infrastructure.config.db.repository.AccountTypeRepository;
import com.bank.transactionaccount.infrastructure.config.db.repository.TransactionRepository;
import com.bank.transactionaccount.infrastructure.config.db.repository.TransactionTypeRepository;
import com.bank.transactionaccount.infrastructure.kafka.report.ReportRequestProducer;
import com.bank.transactionaccount.infrastructure.kafka.report.ReportResponseConsumer;
import com.bank.transactionaccount.infrastructure.transaction.gateway.TransactionDatabaseGateway;
import com.bank.transactionaccount.infrastructure.transactiontype.gateway.TransactionTypeDatabaseGateway;
import com.bank.transactionaccount.usecase.account.*;
import com.bank.transactionaccount.usecase.accounttype.*;
import com.bank.transactionaccount.usecase.report.GenerateReportAccountStatementUseCase;
import com.bank.transactionaccount.usecase.transaction.*;
import com.bank.transactionaccount.usecase.transaction.rules.TransactionBusinessRuleValidator;
import com.bank.transactionaccount.usecase.transaction.rules.validator.AccountBalanceValidator;
import com.bank.transactionaccount.usecase.transactiontype.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

@Configuration
public class MvcConfig {
    // Account
    @Bean
    public CreateAccountUseCase createAccountUseCase(AccountRepository accountRepository, AccountTypeRepository accountTypeRepository) {
        AccountGateway accountGateway = new AccountDatabaseGateway(accountRepository);
        AccountTypeGateway accountTypeGateway = new AccountTypeDatabaseGateway(accountTypeRepository);
        return new CreateAccountUseCase(accountGateway, accountTypeGateway);
    }

    @Bean
    public DeleteAccountUseCase deleteAccountUseCase(AccountRepository accountRepository) {
        AccountGateway accountGateway = new AccountDatabaseGateway(accountRepository);
        return new DeleteAccountUseCase(accountGateway);
    }

    @Bean
    public GetAccountUseCase getAccountUseCase(AccountRepository accountRepository) {
        AccountGateway accountGateway = new AccountDatabaseGateway(accountRepository);
        return new GetAccountUseCase(accountGateway);
    }

    @Bean
    public PaginateAccountUseCase listAccountUseCase(AccountRepository accountRepository) {
        AccountGateway accountGateway = new AccountDatabaseGateway(accountRepository);
        return new PaginateAccountUseCase(accountGateway);
    }

    @Bean
    public UpdateAccountUseCase updateAccountUseCase(AccountRepository accountRepository) {
        AccountGateway accountGateway = new AccountDatabaseGateway(accountRepository);
        return new UpdateAccountUseCase(accountGateway);
    }

    // AccountType
    @Bean
    public CreateAccountTypeUseCase createAccountTypeUseCase(AccountTypeRepository accountTypeRepository) {
        AccountTypeGateway accountTypeGateway = new AccountTypeDatabaseGateway(accountTypeRepository);
        return new CreateAccountTypeUseCase(accountTypeGateway);
    }
    @Bean
    public DeleteAccountTypeUseCase deleteAccountTypeUseCase(AccountTypeRepository accountTypeRepository) {
        AccountTypeGateway accountTypeGateway = new AccountTypeDatabaseGateway(accountTypeRepository);
        return new DeleteAccountTypeUseCase(accountTypeGateway);
    }
    @Bean
    public GetAccountTypeUseCase getAccountTypeUseCase(AccountTypeRepository accountTypeRepository) {
        AccountTypeGateway accountTypeGateway = new AccountTypeDatabaseGateway(accountTypeRepository);
        return new GetAccountTypeUseCase(accountTypeGateway);
    }
    @Bean
    public ListAccountTypeUseCase listAccountTypeUseCase(AccountTypeRepository accountTypeRepository) {
        AccountTypeGateway accountTypeGateway = new AccountTypeDatabaseGateway(accountTypeRepository);
        return new ListAccountTypeUseCase(accountTypeGateway);
    }
    @Bean
    public UpdateAccountTypeUseCase udpateAccountTypeUseCase(AccountTypeRepository accountTypeRepository) {
        AccountTypeGateway accountTypeGateway = new AccountTypeDatabaseGateway(accountTypeRepository);
        return new UpdateAccountTypeUseCase(accountTypeGateway);
    }

    // Transaction
    @Bean
    public CreateTransactionUseCase createTransactionUseCase(TransactionRepository transactionRepository,
                                                             TransactionTypeRepository transactionTypeRepository,
                                                             AccountRepository accountRepository,
                                                             List<TransactionBusinessRuleValidator> businessRuleValidators) {
        TransactionGateway transactionGateway = new TransactionDatabaseGateway(transactionRepository);
        TransactionTypeGateway transactionTypeGateway = new TransactionTypeDatabaseGateway(transactionTypeRepository);
        AccountGateway accountGateway = new AccountDatabaseGateway(accountRepository);
        return new CreateTransactionUseCase(transactionGateway, transactionTypeGateway, accountGateway, businessRuleValidators);
    }

    @Bean
    public List<TransactionBusinessRuleValidator> businessRuleValidators() {
        return List.of(
                new AccountBalanceValidator()
        );
    }

    @Bean
    public DeleteTransactionUseCase deleteTransactionUseCase(TransactionRepository transactionRepository, CreateTransactionUseCase createTransactionUseCase) {
        TransactionGateway transactionGateway = new TransactionDatabaseGateway(transactionRepository);
        return new DeleteTransactionUseCase(transactionGateway, createTransactionUseCase);
    }

    @Bean
    public GetTransactionUseCase getTransactionUseCase(TransactionRepository transactionRepository) {
        TransactionGateway transactionGateway = new TransactionDatabaseGateway(transactionRepository);
        return new GetTransactionUseCase(transactionGateway);
    }

    @Bean
    public PaginateTransactionUseCase paginateTransactionUseCase(TransactionRepository transactionRepository) {
        TransactionGateway transactionGateway = new TransactionDatabaseGateway(transactionRepository);
        return new PaginateTransactionUseCase(transactionGateway);
    }

    @Bean
    public UpdateTransactionUseCase updateTransactionUseCase(TransactionRepository transactionRepository, CreateTransactionUseCase createTransactionUseCase) {
        TransactionGateway transactionGateway = new TransactionDatabaseGateway(transactionRepository);
        return new UpdateTransactionUseCase(transactionGateway, createTransactionUseCase);
    }

    // TransactionType
    @Bean
    public CreateTransactionTypeUseCase createTransactionTypeUseCase(TransactionTypeRepository transactionTypeRepository) {
        TransactionTypeGateway transactionTypeGateway = new TransactionTypeDatabaseGateway(transactionTypeRepository);
        return new CreateTransactionTypeUseCase(transactionTypeGateway);
    }
    @Bean
    public DeleteTransactionTypeUseCase deleteTransactionTypeUseCase(TransactionTypeRepository transactionTypeRepository) {
        TransactionTypeGateway transactionTypeGateway = new TransactionTypeDatabaseGateway(transactionTypeRepository);
        return new DeleteTransactionTypeUseCase(transactionTypeGateway);
    }
    @Bean
    public GetTransactionTypeUseCase getTransactionTypeUseCase(TransactionTypeRepository transactionTypeRepository) {
        TransactionTypeGateway transactionTypeGateway = new TransactionTypeDatabaseGateway(transactionTypeRepository);
        return new GetTransactionTypeUseCase(transactionTypeGateway);
    }
    @Bean
    public ListTransactionTypeUseCase listTransactionTypeUseCase(TransactionTypeRepository transactionTypeRepository) {
        TransactionTypeGateway transactionTypeGateway = new TransactionTypeDatabaseGateway(transactionTypeRepository);
        return new ListTransactionTypeUseCase(transactionTypeGateway);
    }
    @Bean
    public UpdateTransactionTypeUseCase updateTransactionTypeUseCase(TransactionTypeRepository transactionTypeRepository) {
        TransactionTypeGateway transactionTypeGateway = new TransactionTypeDatabaseGateway(transactionTypeRepository);
        return new UpdateTransactionTypeUseCase(transactionTypeGateway);
    }

    // Reportes
    @Bean
    public GenerateReportAccountStatementUseCase generateReportAccountStatementUseCase(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository,
            ReportRequestProducer reportRequestProducer,
            ReportResponseConsumer reportResponseConsumer
    ) {
        AccountGateway accountGateway = new AccountDatabaseGateway(accountRepository);
        TransactionGateway transactionGateway = new TransactionDatabaseGateway(transactionRepository);

        return new GenerateReportAccountStatementUseCase(
                accountGateway,
                transactionGateway,
                reportRequestProducer,
                reportResponseConsumer
        );
    }

    @Bean
    public ReportRequestProducer reportRequestProducer(KafkaTemplate<String, String> kafkaTemplate) {
        return new ReportRequestProducer(kafkaTemplate);
    }

    @Bean
    public ReportResponseConsumer reportResponseConsumer() {
        return new ReportResponseConsumer();
    }
}
