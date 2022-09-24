package com.bravura.finco.service.impls;

import com.bravura.finco.entity.QueryData;
import com.bravura.finco.model.NLPResponse;
import com.bravura.finco.model.FincoResponse;
import com.bravura.finco.repository.QueryRepository;
import com.bravura.finco.service.NLPService;
import com.bravura.finco.service.ProductService;
import com.bravura.finco.utils.JsonFlatner;
import com.vdurmont.emoji.EmojiParser;
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
    public FincoResponse getNlp(@NonNull String searchString, Boolean isAlexa) {
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
            if(Objects.isNull(fincoResponse.getNlpResponse().getPROD()) && checkForAutomatedQueryResponse(fincoResponse, isAlexa)) {
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
                if(Objects.isNull(fincoResponse.getQueryResponse()))  {
                    fincoResponse.setQueryResponse(EmojiParser.parseToUnicode("Sorry :disappointed_relieved: we couldn't find data you are asking."));
                }
            }

        } catch (Exception e) {
            fincoResponse.setData(null);
            fincoResponse.setQueryResponse(EmojiParser.parseToUnicode("Sorry :disappointed_relieved: we couldn't find data you are asking."));
            return fincoResponse;
        }
        return fincoResponse;
    }

    /* Automated Response */
    private boolean checkForAutomatedQueryResponse(FincoResponse fincoResponse , Boolean isAlexa) {
        if(fincoResponse.getNlpResponse().getIntent().equals("greet_gene")) {
            if (isAlexa) {
                fincoResponse.setQueryResponse(EmojiParser.parseToUnicode("Hello user welcome to Finco. how may I help you?"));
            } else {
                fincoResponse.setQueryResponse(EmojiParser.parseToUnicode("Hello user :wave: welcome to <b>Finco</b>. how may I help you :information_desk_person:"));
            }
            return true;
        }
        if(fincoResponse.getNlpResponse().getIntent().equals("greet_gm")) {
            if (isAlexa) {
                fincoResponse.setQueryResponse(EmojiParser.parseToUnicode("good morning"));
            } else {
                fincoResponse.setQueryResponse(EmojiParser.parseToUnicode("good morning :high_brightness:"));
            }
            return true;
        }
        if(fincoResponse.getNlpResponse().getIntent().equals("greet_gev")) {
            if (isAlexa) {
                fincoResponse.setQueryResponse(EmojiParser.parseToUnicode("good afternoon"));
            } else {
                fincoResponse.setQueryResponse(EmojiParser.parseToUnicode("good afternoon :high_brightness:"));
            }
            return true;
        }
        if(fincoResponse.getNlpResponse().getIntent().equals("greet_af")) {
            if (isAlexa) {
                fincoResponse.setQueryResponse(EmojiParser.parseToUnicode("good afternoon"));
            } else {
                fincoResponse.setQueryResponse(EmojiParser.parseToUnicode("good afternoon :sun_with_face:"));
            }
            return true;
        }
        if(fincoResponse.getNlpResponse().getIntent().equals("greet_ques")) {
            if (isAlexa) {
                fincoResponse.setQueryResponse(EmojiParser.parseToUnicode("I am doing fine. What about you?"));
            } else {
                fincoResponse.setQueryResponse(EmojiParser.parseToUnicode("I am doing fine. What about you :female_bow:"));
            }
            return true;
        }
        return false;
    }
}
