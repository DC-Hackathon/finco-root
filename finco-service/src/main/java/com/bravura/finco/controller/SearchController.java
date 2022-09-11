package com.bravura.finco.controller;

import com.bravura.finco.model.asset.FincoResponse;
import com.bravura.finco.service.DistributionService;
import com.bravura.finco.service.NLPService;
//import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/api",produces="application/json")
public class SearchController {

    private final NLPService nlpService;
    private final DistributionService distributionService;

    public SearchController(NLPService nlpService, DistributionService distributionService) {
        this.nlpService = nlpService;
        this.distributionService = distributionService;
    }

    @PostMapping("/search")
    public FincoResponse postFromFlask(@RequestBody String text){
        return nlpService.getNlp(text);
    }

}

