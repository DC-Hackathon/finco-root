package com.bravura.finco.controller;

import com.bravura.finco.service.NlpService;
//import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.function.Function;

@RestController
//@Api(value = "Api test swagger.")
@RequestMapping("/api")
public class SearchController {

    public final NlpService nlpService;

    public SearchController(NlpService nlpService) {
        this.nlpService = nlpService;
    }

    @GetMapping("/getTest/{id}")
    public String getOddEven(@PathParam("id")Integer id){

        Function<Integer, String> evnOrOdd = x -> x%2==0?"even":"odd";
        return " sdadadadad => "+evnOrOdd.apply(id);
    }

    @PostMapping("/search")
    public ResponseEntity<Object> postFromFlask(@RequestBody String text){
        Object nlp = nlpService.getNlp(text);
        return ResponseEntity.status(HttpStatus.OK).body(nlp);
    }
}

