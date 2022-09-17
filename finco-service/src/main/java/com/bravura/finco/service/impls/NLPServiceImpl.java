package com.bravura.finco.service.impls;

import com.bravura.finco.entity.QueryData;
import com.bravura.finco.model.NLPResponse;
import com.bravura.finco.model.FincoResponse;
import com.bravura.finco.repository.QueryRepository;
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

    @Autowired
    private QueryRepository queryRepository;

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
        /* Getting response from nlp */
        try {
            NLPResponse nlpResponseBean = webClient
                    .post()
                    .uri("/nlp/predict" + "?text=" + searchString)
                    .retrieve()
                    .bodyToMono(NLPResponse.class)
                    .block();
            fincoResponse.setNlpResponse(nlpResponseBean);
            if(checkForAutomatedQueryResponse(fincoResponse)) {
                return fincoResponse;
            }
        } catch (Exception e) {
            fincoResponse.setNlpResponse(null);
            return fincoResponse;
        }
        /* Getting response from service */
        try {
            if (Objects.nonNull(fincoResponse.getNlpResponse())) {
                /* If nlp not able to label query then save it to database */
                if(fincoResponse.getNlpResponse().getSER()==null || fincoResponse.getNlpResponse().getPROD()==null || fincoResponse.getNlpResponse().getID()==null) {
                    QueryData queryData= new QueryData();
                    queryData.setQuery(searchString);
                    queryRepository.save(queryData);
                }
                  fincoResponse = this.productService.getProduct(fincoResponse);
            }

        } catch (Exception e) {
            fincoResponse.setNlpResponse(null);
            fincoResponse.setData(null);
            return fincoResponse;
        }
        return fincoResponse;

    }

    /* Automated Response */
    private boolean checkForAutomatedQueryResponse(FincoResponse fincoResponse) {
        if(fincoResponse.getNlpResponse().getIntent().equals("greet_gene")) {
            fincoResponse.setQueryResponse("hello you! How may I help you ?");
            return true;
        }
        if(fincoResponse.getNlpResponse().getIntent().equals("greet_gm")) {
            fincoResponse.setQueryResponse("good morning!");
            return true;
        }
        if(fincoResponse.getNlpResponse().getIntent().equals("greet_gev")) {
            fincoResponse.setQueryResponse("good evening!");
            return true;
        }
        if(fincoResponse.getNlpResponse().getIntent().equals("greet_af")) {
            fincoResponse.setQueryResponse("good afternoon!");
            return true;
        }
        if(fincoResponse.getNlpResponse().getIntent().equals("greet_ques")) {
            fincoResponse.setQueryResponse("I am doing fine :) hope you are doing well too! ");
            return true;
        }
        return false;
    }
}
