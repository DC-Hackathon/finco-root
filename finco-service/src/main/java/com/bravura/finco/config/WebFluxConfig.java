package com.bravura.finco.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Slf4j
public class WebFluxConfig implements WebFluxConfigurer {

    @Value("${nlp.url}")
    String nlpUrl;

    @Value("${dist.url}")
    String distUrl;

    @Value("${dist.bearer}")
    String distToken;

    @Bean
    @Primary
    @Qualifier("nlp")
    public WebClient setNlpWebClient(){
        log.info("NLP URL : {}", nlpUrl);
        return WebClient.builder()
                .baseUrl(nlpUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    @Qualifier("dist")
    public WebClient distribution(){
        log.info("Distribution URL : {}", distUrl);
        return WebClient.builder()
                .baseUrl(distUrl)
                .defaultHeaders(httpHeaders -> httpHeaders.setBearerAuth(distToken))
                .defaultHeader(MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
