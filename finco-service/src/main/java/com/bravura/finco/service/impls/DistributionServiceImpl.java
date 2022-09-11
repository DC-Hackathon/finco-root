package com.bravura.finco.service.impls;

import com.bravura.finco.service.DistributionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DistributionServiceImpl implements DistributionService {

    private final static Logger log = LoggerFactory.getLogger(DistributionServiceImpl.class);

    @Qualifier("dist")
    private final WebClient webClient;

    @Autowired
    public DistributionServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Object callDistributionService(String uri) {
        return webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
