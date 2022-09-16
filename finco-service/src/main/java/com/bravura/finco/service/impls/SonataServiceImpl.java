package com.bravura.finco.service.impls;

import com.bravura.finco.model.GetClientResponse;
import com.bravura.finco.model.NLPResponse;
import com.bravura.finco.service.SonataService;
import com.bravura.finco.utils.JsonFlatner;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class SonataServiceImpl implements SonataService {

    @Qualifier("sbs")
    @Autowired
    private final WebClient webClient;

    public SonataServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Object callSonataProduct(NLPResponse nlpResponse) {
        /*  calling distribution details service */
        String searchID = nlpResponse.getID();
        String response;
        if(nlpResponse.getSER().equals("client")){
            val body =  "{ \"callerDetails\": { \"username\": \"admin\", \"country\": \"IN\", \"language\": \"EN\" }, \"client\": { \"id\": " + searchID + " }, \"includeClientDetails\": true}";
            GetClientResponse clientResponse = this.webClient
                    .post()
                    .uri("/clientService/getClient")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(body))
                    .retrieve()
                    .bodyToMono(GetClientResponse.class)
                    .block();
            System.out.println(clientResponse.getClientDetails());
            Map<String, Object> flattenClientResponse = JsonFlatner.mapToFlat(clientResponse.getClientDetails().toString());
            for (Map.Entry<String, Object> entry : flattenClientResponse.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key.contains(nlpResponse.getIntent())) {
                    return value;
                }
            }
            return null;
        }

        if(nlpResponse.getSER().equals("account")){
            val body ="{ \"callerDetails\": { \"username\": \"admin\", \"country\": \"IN\", \"language\": \"EN\" }, \"accountIdentifier\": { \"accountNumber\": { \"accountNo\": " + searchID + "} }}";
            return this.webClient
                    .post()
                    .uri("/accountService/getAccount")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(body))
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }
        return null;
    }
}
