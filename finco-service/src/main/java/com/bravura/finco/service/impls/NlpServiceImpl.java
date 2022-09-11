package com.bravura.finco.service.impls;

import com.bravura.finco.exception.TechnicalException;
import com.bravura.finco.model.NlpResponse;
import com.bravura.finco.model.asset.FincoResponse;
import com.bravura.finco.service.NlpService;
import com.bravura.finco.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class NlpServiceImpl implements NlpService {

    private final static Logger log = LoggerFactory.getLogger(NlpServiceImpl.class);

    @Autowired
    private ProductService productService;

    @Qualifier("nlp")
    private final WebClient webClient;

    @Autowired
    public NlpServiceImpl( WebClient webClient){
        this.webClient = webClient;
    }

    @Override
    public <T> T getNlp(String searchString) {
        if (searchString == null)
            throw new TechnicalException("Search string is null", HttpStatus.BAD_REQUEST);
        log.info(" looking for results from nlp ...");
        NlpResponse nlpResponseBean = webClient
                .post()
                .uri("/nlp/predict" + "?text=" + searchString)
                .retrieve()
                .bodyToMono(NlpResponse.class)
                .block();
        FincoResponse<Object> fincoResponse = FincoResponse.builder().nlpResponse(nlpResponseBean)
                .data(this.productService.getProduct(nlpResponseBean))
                .build();
        return (T) fincoResponse;

    }
}
