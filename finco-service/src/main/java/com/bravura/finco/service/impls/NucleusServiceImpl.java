package com.bravura.finco.service.impls;

import com.bravura.finco.model.NLPResponse;
import com.bravura.finco.service.NucleusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NucleusServiceImpl implements NucleusService {

    @Qualifier("nucleus")
    @Autowired
    private final WebClient webClient;

    public NucleusServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Object callNucleusProduct(NLPResponse nlpResponse){
        return this.webClient
                .post()
                .uri("/client/getClient")
                .contentType(MediaType.APPLICATION_JSON)
               // .body(BodyInserters.fromObject(body))
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

}
