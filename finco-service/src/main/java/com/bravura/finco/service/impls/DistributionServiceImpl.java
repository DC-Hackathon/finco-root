package com.bravura.finco.service.impls;

import com.bravura.finco.constant.DistributionServiceType;
import com.bravura.finco.model.FincoResponse;
import com.bravura.finco.model.NLPResponse;
import com.bravura.finco.model.security.AuthToken;
import com.bravura.finco.service.DistributionService;
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

    public DistributionServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public FincoResponse callDistributionProduct(FincoResponse fincoResponse) {

        /*  generating a token if not present in cache */
        String token;
        if (systemCache.getIfPresent(DSTR_ACCESS_TOKEN) == null) {
            AuthToken bearer = this.webClient
                    .get()
                    .uri(bearerURI)
                    .retrieve()
                    .bodyToMono(AuthToken.class)
                    .block();
            systemCache.put(DSTR_ACCESS_TOKEN, bearer.getAccess_token());
            token = systemCache.getIfPresent(DSTR_ACCESS_TOKEN);
        } else {
            token = systemCache.getIfPresent(DSTR_ACCESS_TOKEN);
        }

        /*  calling distribution details service with voucher id */
        if (fincoResponse.getNlpResponse().getSER().equalsIgnoreCase(DistributionServiceType.DISTRIBUTION_DATA_WITH_ID.getCode())) {
             Object data  = this.webClient
                    .get()
                    .uri("voucher/" + fincoResponse.getNlpResponse().getID() + "/entitlement-details?loadNomineeDetails=true&loadAccountDetails=true")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }


        /*  calling nominee details service with id*/
        if (fincoResponse.getNlpResponse().getSER().equalsIgnoreCase(DistributionServiceType.NOMINEE_DETAILS_WITH_ID.getCode())) {
            Object data= this.webClient
                    .get()
                    .uri("/nominee/"+ fincoResponse.getNlpResponse().getID())
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }


        /*  calling product details service with id */
        if (fincoResponse.getNlpResponse().getSER().equalsIgnoreCase(DistributionServiceType.PRODUCT_DETAILS_WITH_ID.getCode())) {
            Object data= this.webClient
                    .get()
                    .uri("/product/"+ fincoResponse.getNlpResponse().getID())
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }

        /*  calling asset details service with id */
        if (fincoResponse.getNlpResponse().getSER().equalsIgnoreCase(DistributionServiceType.ASSET_DETAILS_WITH_ID.getCode())) {
            Object data= this.webClient
                    .get()
                    .uri("/asset/GB00BP8Y4W80")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }

        return null;
    }
}
