package com.bravura.finco.service.impls;

import com.bravura.finco.exception.TechnicalException;
import com.bravura.finco.model.FincoResponse;
import com.bravura.finco.model.GetAccountDetailsResponse;
import com.bravura.finco.model.GetClientResponse;
import com.bravura.finco.model.NLPResponse;
import com.bravura.finco.service.SonataService;
import com.bravura.finco.utils.ConvertObjectToJson;
import com.bravura.finco.utils.JsonFlatner;
import com.google.gson.Gson;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Optional;

@Service
public class SonataServiceImpl implements SonataService {

    @Qualifier("sbs")
    @Autowired
    private final WebClient webClient;

    public SonataServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public FincoResponse callSonataProduct(FincoResponse fincoResponse) {
        /*  calling distribution details service */
        String searchID = fincoResponse.getNlpResponse().getID();
        String response;
        if(fincoResponse.getNlpResponse().getSER().equals("client")){
            val body =  "{ \"callerDetails\": { \"username\": \"admin\", \"country\": \"IN\", \"language\": \"EN\" }, \"client\": { \"id\": " + searchID + " }, \"includeClientDetails\": true}";
            Optional<GetClientResponse> clientResponse = Optional.ofNullable(this.webClient
                    .post()
                    .uri("/clientService/getClient")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(body))
                    .retrieve()
                    .bodyToMono(GetClientResponse.class)
                    .block());
            Map<String, Object> flattenClientResponse = getStringObjectMap
                    (clientResponse.orElseThrow(() -> new TechnicalException("client response is null")));
            fincoResponse.setData(flattenClientResponse);
            fincoResponse.setNlpResponse(fincoResponse.getNlpResponse());
            for (Map.Entry<String, Object> entry : flattenClientResponse.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key.contains(fincoResponse.getNlpResponse().getIntent())) {
                    fincoResponse.setIntentData(value);
                }
            }
            return fincoResponse;
        }

        if(fincoResponse.getNlpResponse().getSER().equals("account")){
            val body ="{ \"callerDetails\": { \"username\": \"admin\", \"country\": \"IN\", \"language\": \"EN\" }, \"accountIdentifier\": { \"accountNumber\": { \"accountNo\": " + searchID + "} }}";
            Optional<GetAccountDetailsResponse> accountDetailsResponse = Optional.ofNullable(this.webClient
                    .post()
                    .uri("/accountService/getAccount")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(body))
                    .retrieve()
                    .bodyToMono(GetAccountDetailsResponse.class)
                    .block());
            Map<String, Object> flattenClientResponse = getStringObjectMap
                    (accountDetailsResponse.orElseThrow(() -> new TechnicalException("client response is null")));
            fincoResponse.setData(flattenClientResponse);
            for (Map.Entry<String, Object> entry : flattenClientResponse.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key.contains(fincoResponse.getNlpResponse().getIntent())) {
                    fincoResponse.setIntentData(value);
                }
            }
            return fincoResponse;
        }
        return null;
    }

    private static Map<String, Object> getStringObjectMap(Object response) {
        Map<String, Object> flattenClientResponse = JsonFlatner
                .mapToFlat(
                        ConvertObjectToJson.convertToJson(response
                        ));
        return flattenClientResponse;
    }
}
