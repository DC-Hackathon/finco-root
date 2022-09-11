package com.bravura.finco.controller;

import com.bravura.finco.service.DistributionService;
import com.bravura.finco.service.NlpService;
//import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.function.Function;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class SearchController {

    private final NlpService nlpService;
    private final DistributionService distributionService;

    public SearchController(NlpService nlpService, DistributionService distributionService) {
        this.nlpService = nlpService;
        this.distributionService = distributionService;
    }

    @GetMapping("/getTest/{id}")
    public String getOddEven(@PathParam("id")Integer id){

        Function<Integer, String> evnOrOdd = x -> x%2==0?"even":"odd";
        return " sdadadadad => "+evnOrOdd.apply(id);
    }

    @PostMapping("/search")
    public Object postFromFlask(@RequestBody String text){
        return nlpService.getNlp(text);
    }

    @GetMapping("/product/{product}")
    public ResponseEntity<Object> callDistribution(@PathVariable("product") String product){
        return ResponseEntity.status(HttpStatus.OK).body(distributionService.callDistributionService(product));
    }
}

