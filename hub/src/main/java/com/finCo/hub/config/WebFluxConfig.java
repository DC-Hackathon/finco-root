package com.finCo.hub.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Slf4j
public class WebFluxConfig implements WebFluxConfigurer {

    @Value("${nlp.url}")
    String nlpUrl;

    @Bean
    public WebClient setNlpWebClient(){
        log.info("NLP URL : {}", nlpUrl);
        return WebClient.builder()
                .baseUrl(nlpUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


}
