package com.bravura.finco.utils;

import com.bravura.finco.exception.TechnicalException;
import com.github.wnameless.json.flattener.JsonFlattener;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

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
}
