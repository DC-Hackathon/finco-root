package com.finCo.hub.controller;

import com.finCo.hub.service.NlpService;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.HttpResource;

import javax.websocket.server.PathParam;
import java.util.function.Function;

@RestController
@Api(value = "Api test swagger.")
@RequestMapping("/api")
public class TestController {

    public final NlpService nlpService;

    public TestController(NlpService nlpService) {
        this.nlpService = nlpService;
    }

    @GetMapping("/getTest/{id}")
    public String getOddEven(@PathParam("id")Integer id){

        Function<Integer, String> evnOrOdd = x -> x%2==0?"even":"odd";
        return " sdadadadad => "+evnOrOdd.apply(id);
    }

    @PostMapping("/search")
    public ResponseEntity<JsonObject> postFromFlask(@PathParam("text") String text){
        JsonObject nlp = nlpService.getNlp(text);
        return ResponseEntity.status(HttpStatus.OK).body(nlp);
    }
}

