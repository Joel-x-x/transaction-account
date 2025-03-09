package com.bank.transactionaccount.infrastructure.account.controller;

import com.bank.transactionaccount.entity.account.model.Account;
import com.bank.transactionaccount.infrastructure.account.dto.AccountPublicData;
import com.bank.transactionaccount.usecase.account.PaginateAccountUseCase;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class PaginateAccountController {

    private final PaginateAccountUseCase paginateAccountUseCase;

    public PaginateAccountController(PaginateAccountUseCase paginateAccountUseCase) {
        this.paginateAccountUseCase = paginateAccountUseCase;
    }

    @GetMapping("${api.prefix}/accounts/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public Page<AccountPublicData> paginateAccounts(
            @PathVariable String customerId,
            @RequestParam int page,
            @RequestParam int size
            ) {
        Page<Account> customers = this.paginateAccountUseCase.execute(page, size, customerId);

        return customers.map(AccountPublicData::new);
    }
}
