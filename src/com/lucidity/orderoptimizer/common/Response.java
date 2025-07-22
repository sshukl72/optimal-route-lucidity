package com.lucidity.orderoptimizer.common;

public class Response<T> {
    private T data;
    private String errorMessage;

    private Response(T data, String errorMessage) {
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(data, null);
    }

    public static <T> Response<T> failure(String errorMessage) {
        return new Response<>(null, errorMessage);
    }

    public T getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean hasError() {
        return errorMessage != null;
    }


}
