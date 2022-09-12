package com.bravura.finco.service.impls;

import com.bravura.finco.exception.TechnicalException;
import com.bravura.finco.model.NLPResponse;
import com.bravura.finco.model.asset.FincoResponse;
import com.bravura.finco.service.NLPService;
import com.bravura.finco.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Objects;

@Service
public class NLPServiceImpl implements NLPService {

    private final static Logger log = LoggerFactory.getLogger(NLPServiceImpl.class);

    @Autowired
    private ProductService productService;

    @Qualifier("nlp")
    private final WebClient webClient;

    @Autowired
    public NLPServiceImpl(WebClient webClient){
        this.webClient = webClient;
    }

    @Override
    public FincoResponse getNlp(String searchString) {
        if (searchString == null)
            throw new TechnicalException("Search string is null", HttpStatus.BAD_REQUEST);
        log.info(" looking for results from nlp ...");
        FincoResponse fincoResponse= new FincoResponse();
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
            return fincoResponse;
        }
        try {
            if(Objects.nonNull(fincoResponse.getNlpResponse())) {
                fincoResponse.setData(this.productService.getProduct(fincoResponse.getNlpResponse()));
            }
        } catch (Exception e) {
            fincoResponse.setData(null);
            return fincoResponse;
        }
        return  fincoResponse;

    }
}
