package com.toy.pick.api;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {

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
