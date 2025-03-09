package com.bank.transactionaccount.infrastructure.report.controller;

import com.bank.transactionaccount.entity.account.exception.AccountNotFoundException;
import com.bank.transactionaccount.usecase.report.GenerateReportAccountStatementUseCase;
import com.bank.transactionaccount.usecase.report.dto.IReportAccountStatementPublicData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
public class GenerateReportAccountStatementController {

    private final GenerateReportAccountStatementUseCase generateReportAccountStatementUseCase;

    public GenerateReportAccountStatementController(GenerateReportAccountStatementUseCase generateReportAccountStatementUseCase) {
        this.generateReportAccountStatementUseCase = generateReportAccountStatementUseCase;
    }

    @GetMapping("${api.prefix}/reports")
    @ResponseStatus(HttpStatus.OK)
    public IReportAccountStatementPublicData genereReportAccountStatement(
            @RequestParam String customerId,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end
            ) throws AccountNotFoundException {
        return this.generateReportAccountStatementUseCase.execute(customerId, start, end);
    }
}
