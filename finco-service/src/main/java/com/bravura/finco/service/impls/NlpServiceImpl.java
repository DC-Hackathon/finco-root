package com.bravura.finco.service.impls;

import com.bravura.finco.exception.TechnicalException;
import com.bravura.finco.service.NlpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NlpServiceImpl implements NlpService {

    private final static Logger log = LoggerFactory.getLogger(NlpServiceImpl.class);

    @Qualifier("nlp")
    private final WebClient webClient;

    @Autowired
    public NlpServiceImpl( WebClient webClient){
        this.webClient = webClient;
    }

    @Override
    public Object getNlp(String searchString) {
        if (searchString == null)
            throw new TechnicalException("Search string is null", HttpStatus.BAD_REQUEST);

        log.info("looking for results.");

        return webClient
                .post()
                .uri("/nlp/predict"+"?text="+searchString)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
