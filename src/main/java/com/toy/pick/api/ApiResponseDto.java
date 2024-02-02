package com.toy.pick.api;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.Annotation;

@Getter
@ToString
public class ApiResponseDto<T>  {
    private final HttpStatus status; // 2XX, 4XX ...
    private final int code;
    private final String StatusMessage;
    private final String message;
    private final T data;

    public ApiResponseDto(HttpStatus httpStatus,  String message, T data, String statusMessage) {
        this.code = httpStatus.value();
        this.status = httpStatus;
        this.message = message;
        this.data = data;
        this.StatusMessage = statusMessage;
    }



    public static <T> ApiResponseDto<T> of(HttpStatus httpStatus, T data, String message, String statusMessage) {
        return new ApiResponseDto<>(httpStatus, message, data, statusMessage);
    }


    public static <T> ApiResponseDto<T> ok(T data) {
        return of(HttpStatus.OK, data, "SUCCESS","SUCCESS");
    }


}
