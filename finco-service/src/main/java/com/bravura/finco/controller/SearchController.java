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

    /**
     * Search api takes the user text data which can be typed or spoken by the user
     * and forwards it to the NLP API.
     * @param text is a message typed of spoken by the user.
     * @return <b>FincoResponse</b> which holds the nlp data and the data coming from different services like sonata, distributions etc.
     */

    @ApiResponse(
            responseCode = "200",
            description = "Takes string which can be typed or spoken by the user",
            content = {
                    @Content(mediaType = "application/json")
            }
    )
    @PostMapping("/search")
    public FincoResponse postFromFlask(@RequestBody String text){
        return nlpService.getNlp(text);
    }

}

