package com.bank.transactionaccount.infrastructure.transactiontype.controller;

import com.bank.transactionaccount.entity.transactiontype.model.TransactionType;
import com.bank.transactionaccount.infrastructure.transactiontype.dto.TransactionTypePublicData;
import com.bank.transactionaccount.usecase.transactiontype.ListTransactionTypeUseCase;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListTransactionTypeController {

    private final ListTransactionTypeUseCase listTransactionTypeUseCase;

    public ListTransactionTypeController(ListTransactionTypeUseCase listTransactionTypeUseCase) {
        this.listTransactionTypeUseCase = listTransactionTypeUseCase;
    }

    @GetMapping("${api.prefix}/transaction-types")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionTypePublicData> listTransactionTypes() {
        return this.listTransactionTypeUseCase.execute().stream().map(TransactionTypePublicData::new).toList();
    }
}
