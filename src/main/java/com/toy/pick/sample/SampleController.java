package com.toy.pick.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class SampleController {

    @GetMapping("/sample/get")
    public String createOrder(){
        return "Sample-Get-Test";
    }
}
