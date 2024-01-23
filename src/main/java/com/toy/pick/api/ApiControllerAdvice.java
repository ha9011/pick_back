package com.toy.pick.api;

import com.toy.pick.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomException.class)
    public ApiResponse<Object> customExceptionHandler(CustomException e){
        log.info(":::CustomException Handler:::");
        return ApiResponse.of(HttpStatus.BAD_REQUEST,null, e.getMessage(), "FAIL");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> exceptionHandler(Exception e){
        log.info(":::Exception Handler:::");
        log.info(":::Exception Handler::: " + e.getMessage());


        return ApiResponse.of(HttpStatus.BAD_REQUEST, null,e.getMessage(), "FAIL");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<String> controllerReqBodyValidationErrHandler(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        String defaultMessage = bindingResult.getFieldError().getField()+"은(는) "+bindingResult.getFieldError().getDefaultMessage();
        return ApiResponse.of(HttpStatus.BAD_REQUEST, null, defaultMessage, "FAIL");
    }

//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(JwtException.class)
//    public ApiResponse<Object> jwtException(JwtException e){
//        System.out.println("-----jwtException----");
//        System.out.println(e.getMessage());
//        System.out.println(e);
//
//        return ApiResponse.of(HttpStatus.UNAUTHORIZED, e.getMessage(), null);
//    }


}
