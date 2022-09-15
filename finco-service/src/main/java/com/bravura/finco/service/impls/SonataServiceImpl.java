package com.bravura.finco.service.impls;

import com.bravura.finco.model.NLPResponse;
import com.bravura.finco.service.SonataService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SonataServiceImpl implements SonataService {

    @Qualifier("sbs")
    @Autowired
    private final WebClient webClient;

    public SonataServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Object callSonataProduct(NLPResponse nlpResponse) {
        /*  calling distribution details service */
        val body = "{\n" +
                "\"email\":\"test@mail.com\",\n" +
                "\"id\":1\n" +
                "}";
        return this.webClient
                .post()
                .uri("/accountService/getAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
