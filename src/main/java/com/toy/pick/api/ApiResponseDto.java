package com.toy.pick.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.Annotation;

@Getter
@ToString
public class ApiResponseDto<T>  {
    private final String status;  // TODO 삭제 할듯??
    private final int code;  // 2XX, 4XX ...
    private final String StatusMessage;
    private final String message;
    private final T data;

    public ApiResponseDto(HttpStatus httpStatus,  String message, T data, String statusMessage) {
        this.code = httpStatus.value();
        this.status = httpStatus.name();
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
