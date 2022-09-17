package com.bravura.finco.service.impls;

import com.bravura.finco.model.NLPResponse;
import com.bravura.finco.model.FincoResponse;
import com.bravura.finco.service.NLPService;
import com.bravura.finco.service.ProductService;
import com.bravura.finco.utils.JsonFlatner;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
public class NLPServiceImpl implements NLPService {

    private static final Logger log = LoggerFactory.getLogger(NLPServiceImpl.class);

    @Autowired
    private ProductService productService;

    @Qualifier("nlp")
    private final WebClient webClient;

    @Autowired
    public NLPServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public FincoResponse getNlp(@NonNull String searchString) {

        log.info(" looking for results from nlp ...");
        FincoResponse fincoResponse = new FincoResponse();
        try {
            NLPResponse nlpResponseBean = webClient
                    .post()
                    .uri("/nlp/predict" + "?text=" + searchString)
                    .retrieve()
                    .bodyToMono(NLPResponse.class)
                    .block();
            fincoResponse.setNlpResponse(nlpResponseBean);
           
        } catch (Exception e) {
            fincoResponse.setNlpResponse(null);
        }

        try {
            if (Objects.nonNull(fincoResponse.getNlpResponse())) {
                  fincoResponse = this.productService.getProduct(fincoResponse);
            }

        } catch (Exception e) {
            fincoResponse.setNlpResponse(null);
            fincoResponse.setData(null);
            return fincoResponse;
        }
        return fincoResponse;

    }
}
