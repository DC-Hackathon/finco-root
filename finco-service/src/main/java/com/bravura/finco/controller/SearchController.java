package com.bravura.finco.controller;

import com.bravura.finco.model.FincoResponse;
import com.bravura.finco.service.NLPService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;

/**
 * <b>Search Controller</b> handle the interaction between
 * frontend and service providers.
 */

@RestController
@CrossOrigin("*")
@RequestMapping(value="/api",produces="application/json")
@Slf4j
public class SearchController {

    private final NLPService nlpService;
    public SearchController(NLPService nlpService) {
        this.nlpService = nlpService;
    }

    /**
     * Search api takes the user text data which can be typed or spoken by the user
     * and forwards it to the  python NLP API to filter the tags and then forward it to the service providers such as.
     * <b>Sonata, Distributions, etc...</b>
     * @param text is a message typed of spoken by the user.
     * @return <b>FincoResponse</b> which holds the nlp data and the data coming from different services like sonata, distributions etc.
     */

    @ApiResponse(
            responseCode = "200",
            description = "SuccessfulOperation",
            content = {
                    @Content(mediaType = "application/json")
            }
    )
    @Produces("application/json")
    @GetMapping("/search")
    public FincoResponse postFromFlask(@RequestParam("text") String text, @RequestParam(value = "isAlexa", required = false,defaultValue = "false") Boolean isAlexa){
        return nlpService.getNlp(text,isAlexa);
    }

}

