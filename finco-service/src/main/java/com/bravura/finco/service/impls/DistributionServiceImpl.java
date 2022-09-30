package com.bravura.finco.service.impls;

import com.bravura.finco.constant.DistributionServiceType;
import com.bravura.finco.exception.TechnicalException;
import com.bravura.finco.model.FincoResponse;
import com.bravura.finco.model.security.AuthToken;
import com.bravura.finco.service.DistributionService;
import com.bravura.finco.utils.ConvertObjectToJson;
import com.bravura.finco.utils.JsonFlatner;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class DistributionServiceImpl implements DistributionService {

    public static final String DSTR_ACCESS_TOKEN = "access-token";
    private static final Cache<String, String> systemCache =
            CacheBuilder.newBuilder()
                    .initialCapacity(10)
                    .expireAfterAccess(10, TimeUnit.MINUTES)
                    .build();
    private static final Logger log = LoggerFactory.getLogger(DistributionServiceImpl.class);
    @Qualifier("dist")
    @Autowired
    private final WebClient webClient;
    @Value("${dist.bearer_uri}")
    String bearerURI;

    private String token;

    public DistributionServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public FincoResponse callDistributionProduct(FincoResponse fincoResponse, Boolean isAlexa) {

        /*  generating a token if not present in cache */
        if (systemCache.getIfPresent(DSTR_ACCESS_TOKEN) == null) {
            AuthToken bearer = this.webClient
                    .get()
                    .uri(bearerURI)
                    .retrieve()
                    .bodyToMono(AuthToken.class)
                    .block();
            systemCache.put(DSTR_ACCESS_TOKEN, bearer==null?"Invalid bearer":bearer.getAccess_token());
            this.token = systemCache.getIfPresent(DSTR_ACCESS_TOKEN);
        } else {
            this.token = systemCache.getIfPresent(DSTR_ACCESS_TOKEN);
        }

        /*  calling distribution details service with voucher id */
        if (fincoResponse.getNlpResponse().getSER().equalsIgnoreCase(DistributionServiceType.DISTRIBUTION_DATA_WITH_ID.getCode())) {
             Object data  = getResponseFromDstr("/voucher/" + fincoResponse.getNlpResponse().getID() + "/entitlement-details?loadNomineeDetails=true&loadAccountDetails=true");
            Map<String, Object> flatenResponse = JsonFlatner.mapToFlat(ConvertObjectToJson
                    .convertToJson(data==null?new TechnicalException("Distribution sends an empty response"):data));

            return JsonFlatner.getDataResponse(fincoResponse, flatenResponse, isAlexa);
        }


        /*  calling nominee details service with id*/
        if (fincoResponse.getNlpResponse().getSER().equalsIgnoreCase(DistributionServiceType.NOMINEE_DETAILS_WITH_ID.getCode())) {
            Object data= getResponseFromDstr("/nominee/"+ fincoResponse.getNlpResponse().getID());

            Map<String, Object> flatenResponse = JsonFlatner.mapToFlat(ConvertObjectToJson
                    .convertToJson(data==null?new TechnicalException("Distribution sends an empty response"):data));

            return JsonFlatner.getDataResponse(fincoResponse, flatenResponse, isAlexa);
        }


        /*  calling product details service with id */
        if (fincoResponse.getNlpResponse().getSER().equalsIgnoreCase(DistributionServiceType.PRODUCT_DETAILS_WITH_ID.getCode())) {
            Object data= getResponseFromDstr("/product/"+ fincoResponse.getNlpResponse().getID());
            Map<String, Object> flatenResponse = JsonFlatner.mapToFlat(ConvertObjectToJson
                    .convertToJson(data==null?new TechnicalException("Distribution sends an empty response"):data));

            return JsonFlatner.getDataResponse(fincoResponse, flatenResponse, isAlexa);
        }

        /*  calling asset details service with id */
        if (fincoResponse.getNlpResponse().getSER().equalsIgnoreCase(DistributionServiceType.ASSET_DETAILS_WITH_ID.getCode())) {
            Object data = getResponseFromDstr("/asset/GB00BP8Y4W80");
            if(data == null )
                throw new TechnicalException("Distribution sends an empty response");
            Map<String, Object> flatenResponse = JsonFlatner.mapToFlat(ConvertObjectToJson
                    .convertToJson(data));

            return JsonFlatner.getDataResponse(fincoResponse, flatenResponse,isAlexa);
        }

        return null;
    }

    private Object getResponseFromDstr(String uri){
        try {
            return this.webClient
                    .get()
                    .uri(uri)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.token)
                    .retrieve()
                    .bodyToMono(Optional.class)
                    .block();
        } catch (Exception e) {
            throw new TechnicalException(e.getMessage());
        }
    }
}
