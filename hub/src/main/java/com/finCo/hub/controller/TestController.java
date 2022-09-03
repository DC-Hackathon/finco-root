package com.finCo.hub.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Api test swagger.")
@RequestMapping("/api")
public class TestController {


    @GetMapping("/getTest")
    public String getFromFlask(){
        return " sdadadadad";
    }

    @PostMapping("/getTest")
    public String postFromFlask(){
        return "sdasdadasdwavfa edad awsda weeaw  ";
    }
}

