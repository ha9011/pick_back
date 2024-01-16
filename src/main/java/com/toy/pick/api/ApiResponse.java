package com.toy.pick.api;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {
    private HttpStatus status; // 2XX, 4XX ...
    private int code;
    private String StatusMessage;
    private String message;
    private T data;

    public ApiResponse(HttpStatus httpStatus,  String message, T data, String statusMessage) {
        this.code = httpStatus.value();
        this.status = httpStatus;
        this.message = message;
        this.data = data;
        this.StatusMessage = statusMessage;
    }

    public static <T> ApiResponse<T> of(HttpStatus httpStatus, String message ,T data, String statusMessage) {
        return new ApiResponse<>(httpStatus, message, data, statusMessage);
    }

    public static <T> ApiResponse<T> of(HttpStatus httpStatus, T data, String statusMessage) {
        return of(httpStatus, httpStatus.name(), data, statusMessage);
    }

    public static <T> ApiResponse<T> ok(T data) {
        return of(HttpStatus.OK, data, "SUCCESS");
    }
}
