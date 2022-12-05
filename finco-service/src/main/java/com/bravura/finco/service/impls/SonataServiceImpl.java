package com.bravura.finco.service.impls;

import com.bravura.finco.constant.SonataServiceType;
import com.bravura.finco.exception.TechnicalException;
import com.bravura.finco.model.*;
import com.bravura.finco.service.SonataService;
import com.bravura.finco.utils.ConvertObjectToJson;
import com.bravura.finco.utils.JsonFlatner;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
    public FincoResponse callSonataProduct(FincoResponse fincoResponse, Boolean isAlexa) {
        /*  calling distribution details service */
        if (fincoResponse.getNlpResponse().getSER().equals(SonataServiceType.CLIENT.getCode())) {
            val body = "{ \"callerDetails\": { \"username\": \"admin\", \"country\": \"IN\", \"language\": \"EN\" }, \"client\": { \"id\": " + fincoResponse.getNlpResponse().getID() + " }, \"includeClientDetails\": true, \"includeAddress\": true, \"includeBankAccount\": true}";
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
            return JsonFlatner.getDataResponse(fincoResponse,flattenClientResponse, isAlexa);
        }
        if (fincoResponse.getNlpResponse().getSER().equals(SonataServiceType.ACCOUNT.getCode())) {
            String response = fincoResponse.getNlpResponse().getID().replace(".", "").replaceAll("\\s+","");
            val body = "{ \"callerDetails\": { \"username\": \"admin\", \"country\": \"IN\", \"language\": \"EN\" }, \"accountDetails\": [ { \"account\": { \"accountNumber\": { \"accountNo\":" + "\""  + response + "\""  + "} } } ], \"includeAccountDetail\": true, \"includeProduct\": true, \"includeInvestmentProfile\": true, \"includeAdvisorGroup\": true, \"includeEmploymentDetails\": true, \"includeBalance\": true}";
            Optional<GetAccountDetailsResponse> accountDetailsResponse = Optional.ofNullable(this.webClient
                    .post()
                    .uri("/accountService/getAccountDetails")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(body))
                    .retrieve()
                    .bodyToMono(GetAccountDetailsResponse.class)
                    .block());
            Map<String, Object> flattenClientResponse = getStringObjectMap
                    (accountDetailsResponse.orElseThrow(() -> new TechnicalException("client response is null")));
            return JsonFlatner.getDataResponse(fincoResponse,flattenClientResponse, isAlexa);
        }
        if (fincoResponse.getNlpResponse().getSER().equals(SonataServiceType.ACCOUNTNUMBER.getCode())) {
            String name[] = fincoResponse.getNlpResponse().getID().split("\\s+");
            val body = "{ \"callerDetails\": { \"username\": \"admin\", \"country\": \"IN\", \"language\": \"EN\" }, \"searchCriteria\": { \"client\": { \"clientId\": { \"clientSurname\":" + "\""  + name[1] + "\""  +", \"clientForename\":" + "\""  + name[0] + "\""  +" } },\t\"statuses\": [ { \"code\": \"ACTV\", \"codeType\": \"ASTA\" } ] }, \"pagingRange\": { \"firstResult\": 1, \"resultsPerPage\": 10 }, \"includeClientAcctRelationships\": true}";
            Optional<SearchAccountResponse> searchAccountResponse = Optional.ofNullable(this.webClient
                    .post()
                    .uri("/accountService/searchAccountByCriteria")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(body))
                    .retrieve()
                    .bodyToMono(SearchAccountResponse.class)
                    .block());
            List<String> accountList = new ArrayList<>();
            for (SearchAccountResponseAccounts account : searchAccountResponse.get().getAccounts()) {
               accountList.add( account.getAccountNumber().getAccountNo());
            }
            String accounts = String.join(", ", accountList);
            if (accountList.size() > 1) {
                fincoResponse.setQueryResponse("The account numbers for client " + fincoResponse.getNlpResponse().getID() + " are " + accounts);
            } else {
                fincoResponse.setQueryResponse("The account numbers for client " + fincoResponse.getNlpResponse().getID() + " is " + accounts);
            }
            return fincoResponse;
        }
        return FincoResponse.builder().build();
    }

    /* Utility methods  */
    private static Map<String, Object> getStringObjectMap(Object response) {
        return JsonFlatner
                .mapToFlat(
                        ConvertObjectToJson.convertToJson(response
                        ));
    }
}
