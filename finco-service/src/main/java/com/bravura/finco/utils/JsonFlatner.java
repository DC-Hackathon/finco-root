package com.bravura.finco.utils;

import com.bravura.finco.exception.TechnicalException;
import com.bravura.finco.model.FincoResponse;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.vdurmont.emoji.EmojiParser;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
public class JsonFlatner {

    private JsonFlatner(){}

    public static Map<String, Object> mapToFlat(@NonNull String jsonString) {

        if(jsonString.isEmpty())
            throw new TechnicalException("Parameter can not be empty.");

        Map<String, Object> stringObjectMap = JsonFlattener.flattenAsMap(jsonString);
        stringObjectMap.forEach((k,v) -> log.info(k.concat(":".concat(v.toString()))));

        return stringObjectMap;
    }
    public static FincoResponse getDataResponse(FincoResponse fincoResponse, Map<String, Object> flattenClientResponse, Boolean isAlexa) {
        fincoResponse.setData(flattenClientResponse);
        fincoResponse.setNlpResponse(fincoResponse.getNlpResponse());
        for (Map.Entry<String, Object> entry : flattenClientResponse.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key.contains(fincoResponse.getNlpResponse().getIntent())) {
                String myIntent = setIntent(fincoResponse.getNlpResponse().getIntent());
                String[] intent = myIntent.split("(?=[A-Z])");
                String messageIntent = String.join(" ", intent);
                String response = isAlexa ? value.toString() : "<b>" + value.toString() + "</b>";
                String finalResponse = messageIntent + " for " + fincoResponse.getNlpResponse().getID() + " is " + response;
                fincoResponse.setQueryResponse(finalResponse);
            }
        }
        return fincoResponse;

    }

    static String setIntent(String intent) {
        switch(intent){
            case "line1": return "address";
            case "advisor.name": return "advisor name";
            case "patternTemplate.name": return "investment profile";
            case "account.statusCode": return "status";
            case "accountNumber.accountNo": return "account number";
            default : return intent;
        }
    }
}

