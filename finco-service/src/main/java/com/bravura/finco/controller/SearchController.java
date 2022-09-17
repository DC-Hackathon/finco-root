package com.bravura.finco.controller;

import com.bravura.finco.model.FincoResponse;
import com.bravura.finco.service.NLPService;
import com.bravura.finco.utils.JsonFlatner;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/api",produces="application/json")
public class SearchController {

    private final NLPService nlpService;
    public SearchController(NLPService nlpService) {
        this.nlpService = nlpService;
    }


    @ApiResponse(
            responseCode = "200",
            description = "SuccessfulOperation",
            content = {
                    @Content(mediaType = "application/json")
            }
    )
    @Produces("application/json")
    @PostMapping("/search")
    public FincoResponse postFromFlask(@RequestBody String text){
        return nlpService.getNlp(text);
    }

    @PostMapping("/flatMapToJson")
    public Object mapToString(@RequestBody String jsonObject){
        return JsonFlatner.mapToFlat(jsonObject);
    }
}

