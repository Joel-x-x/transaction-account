package com.bank.transactionaccount.infrastructure.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResultResponse<T, E> {
    private T value;
    private boolean isSuccess;
    private List<E> errors;
    private int code;

    // Constructor privado
    private ResultResponse(T value, boolean isSuccess, List<E> errors, int code) {
        this.value = value;
        this.isSuccess = isSuccess;
        this.errors = errors;
        this.code = code;
    }

    // Métodos de fábrica para éxito
    public static <T, E> ResultResponse<T, E> success(T value, int code) {
        return new ResultResponse<>(value, true, null, code);
    }

    // Métodos de fábrica para errores
    public static <T, E> ResultResponse<T, E> failure(List<E> errors, int code) {
        return new ResultResponse<>(null, false, errors, code);
    }

    public static <T, E> ResultResponse<T, E> failure(T value, List<E> errors, int code) {
        return new ResultResponse<>(value, false, errors, code);
    }
}