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

    @Value("${sonata.url}")
    String sbsUrl;

    @Value("${nucleus.url}")
    String nucleusUrl;

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
//                .defaultHeaders(httpHeaders -> httpHeaders.setBearerAuth("eyJraWQiOiJFcThNX2FuZXY1S01BOWZ1VmZ6R1pIdXk4czZmQlhVMW41LXVKZHI2djRFIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULm15VUFINUVoVWw1Uk8zX2pWdlE2Y1FQNlhSUUNIYm9wZFdpSzhtX3BjcGsiLCJpc3MiOiJodHRwczovL2Rldi0yMTY4OTE0My5va3RhLmNvbS9vYXV0aDIvZGVmYXVsdCIsImF1ZCI6ImFwaTovL2RlZmF1bHQiLCJpYXQiOjE2NjI5NjE4OTAsImV4cCI6MTY2Mjk2NTQ5MCwiY2lkIjoiMG9hMmVmendmdXh6aURGV3Q1ZDciLCJ1aWQiOiIwMHUybXd1encyaWI4SzNSSDVkNyIsInNjcCI6WyJvcGVuaWQiLCJlbWFpbCIsInJvbGVzIiwicHJvZmlsZSJdLCJhdXRoX3RpbWUiOjE2NjI5NjE4ODcsInN1YiI6InAyZ3VwdGEiLCJyb2xlcyI6WyJFdmVyeW9uZSIsIkd1cnUiXX0.aS46rxjhfMDr6LyJhRESlWRyg2zhG3NSQOGRewli-bKHfHpAlbQGO9b8Bb3zOuHlpsMx3k4vPKNDpF4Qi6jnOdNfHIYVDl-0TIaoym_qZmZ5AE-_Rxu876PLTK5Jz86kxOOu5g4k4YEfRVVLytCt_jQOrj6d6o4lYedK5FZc2GBogKbyzdkntP6gGB3FynsLrS40naiViVodyVUatOgg5xCOFaxUOxdgR-cb4XRfNw9ojJli2aToJG5bbPoj-yhMRBMca4nuM2LyV5mIcflJ_qajZlFGm9gM9BscRghbyOCDy4ufubf3ulab9XmThxwV69hknjn5LFOI04_IdIjYnQ"))
                .defaultHeader(MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    @Qualifier("sbs")
    public WebClient Sonata(){
        log.info("SBS URL : {}", sbsUrl);
        return WebClient.builder()
                .baseUrl(sbsUrl)
//                .defaultHeaders(httpHeaders -> httpHeaders.setBearerAuth("eyJraWQiOiJFcThNX2FuZXY1S01BOWZ1VmZ6R1pIdXk4czZmQlhVMW41LXVKZHI2djRFIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULm15VUFINUVoVWw1Uk8zX2pWdlE2Y1FQNlhSUUNIYm9wZFdpSzhtX3BjcGsiLCJpc3MiOiJodHRwczovL2Rldi0yMTY4OTE0My5va3RhLmNvbS9vYXV0aDIvZGVmYXVsdCIsImF1ZCI6ImFwaTovL2RlZmF1bHQiLCJpYXQiOjE2NjI5NjE4OTAsImV4cCI6MTY2Mjk2NTQ5MCwiY2lkIjoiMG9hMmVmendmdXh6aURGV3Q1ZDciLCJ1aWQiOiIwMHUybXd1encyaWI4SzNSSDVkNyIsInNjcCI6WyJvcGVuaWQiLCJlbWFpbCIsInJvbGVzIiwicHJvZmlsZSJdLCJhdXRoX3RpbWUiOjE2NjI5NjE4ODcsInN1YiI6InAyZ3VwdGEiLCJyb2xlcyI6WyJFdmVyeW9uZSIsIkd1cnUiXX0.aS46rxjhfMDr6LyJhRESlWRyg2zhG3NSQOGRewli-bKHfHpAlbQGO9b8Bb3zOuHlpsMx3k4vPKNDpF4Qi6jnOdNfHIYVDl-0TIaoym_qZmZ5AE-_Rxu876PLTK5Jz86kxOOu5g4k4YEfRVVLytCt_jQOrj6d6o4lYedK5FZc2GBogKbyzdkntP6gGB3FynsLrS40naiViVodyVUatOgg5xCOFaxUOxdgR-cb4XRfNw9ojJli2aToJG5bbPoj-yhMRBMca4nuM2LyV5mIcflJ_qajZlFGm9gM9BscRghbyOCDy4ufubf3ulab9XmThxwV69hknjn5LFOI04_IdIjYnQ"))
                .defaultHeader(MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    @Qualifier("nucleus")
    public WebClient Nucleus(){
        log.info("Nucleus URL : {}", nucleusUrl);
        return WebClient.builder()
                .baseUrl(nucleusUrl)
//                .defaultHeaders(httpHeaders -> httpHeaders.setBearerAuth("eyJraWQiOiJFcThNX2FuZXY1S01BOWZ1VmZ6R1pIdXk4czZmQlhVMW41LXVKZHI2djRFIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULm15VUFINUVoVWw1Uk8zX2pWdlE2Y1FQNlhSUUNIYm9wZFdpSzhtX3BjcGsiLCJpc3MiOiJodHRwczovL2Rldi0yMTY4OTE0My5va3RhLmNvbS9vYXV0aDIvZGVmYXVsdCIsImF1ZCI6ImFwaTovL2RlZmF1bHQiLCJpYXQiOjE2NjI5NjE4OTAsImV4cCI6MTY2Mjk2NTQ5MCwiY2lkIjoiMG9hMmVmendmdXh6aURGV3Q1ZDciLCJ1aWQiOiIwMHUybXd1encyaWI4SzNSSDVkNyIsInNjcCI6WyJvcGVuaWQiLCJlbWFpbCIsInJvbGVzIiwicHJvZmlsZSJdLCJhdXRoX3RpbWUiOjE2NjI5NjE4ODcsInN1YiI6InAyZ3VwdGEiLCJyb2xlcyI6WyJFdmVyeW9uZSIsIkd1cnUiXX0.aS46rxjhfMDr6LyJhRESlWRyg2zhG3NSQOGRewli-bKHfHpAlbQGO9b8Bb3zOuHlpsMx3k4vPKNDpF4Qi6jnOdNfHIYVDl-0TIaoym_qZmZ5AE-_Rxu876PLTK5Jz86kxOOu5g4k4YEfRVVLytCt_jQOrj6d6o4lYedK5FZc2GBogKbyzdkntP6gGB3FynsLrS40naiViVodyVUatOgg5xCOFaxUOxdgR-cb4XRfNw9ojJli2aToJG5bbPoj-yhMRBMca4nuM2LyV5mIcflJ_qajZlFGm9gM9BscRghbyOCDy4ufubf3ulab9XmThxwV69hknjn5LFOI04_IdIjYnQ"))
                .defaultHeader(MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
