package com.bravura.finco.service.impls;

import com.bravura.finco.constant.DistributionServiceType;
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
    public Object callDistributionProduct(NLPResponse nlpResponse) {

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

        /*  calling distribution details service */
        if (nlpResponse.getSER().equalsIgnoreCase(DistributionServiceType.ALL_DISTRIBUTIONS_DATA.getCode())) {
            return this.webClient
                    .get()
                    .uri("account-details/report")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }

        /*  calling distribution details service with voucher id */
        if (nlpResponse.getSER().equalsIgnoreCase(DistributionServiceType.DISTRIBUTION_DATA_WITH_ID.getCode())) {
            return this.webClient
                    .get()
                    .uri("voucher/"+nlpResponse.getID()+"/entitlement-details?loadNomineeDetails=true&loadAccountDetails=true")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }

        /*  calling nominee details service */
        if (nlpResponse.getSER().equalsIgnoreCase(DistributionServiceType.NOMINEE_DETAILS.getCode())) {
            return this.webClient
                    .get()
                    .uri("/nominee")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }

        /*  calling nominee details service with id*/
        if (nlpResponse.getSER().equalsIgnoreCase(DistributionServiceType.NOMINEE_DETAILS_WITH_ID.getCode())) {
            return this.webClient
                    .get()
                    .uri("/nominee/"+ nlpResponse.getID())
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }

        /*  calling product details service */
        if (nlpResponse.getSER().equalsIgnoreCase(DistributionServiceType.PRODUCT_DETAILS.getCode())) {
            return this.webClient
                    .get()
                    .uri("/product")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }

        /*  calling product details service with id */
        if (nlpResponse.getSER().equalsIgnoreCase(DistributionServiceType.PRODUCT_DETAILS_WITH_ID.getCode())) {
            return this.webClient
                    .get()
                    .uri("/product/"+ nlpResponse.getID())
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }

        /*  calling asset details service with id */
        if (nlpResponse.getSER().equalsIgnoreCase(DistributionServiceType.ASSET_DETAILS_WITH_ID.getCode())) {
            return this.webClient
                    .get()
                    .uri("/asset/GB00BP8Y4W80")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }

        /*  calling all asset details service */
        if (nlpResponse.getSER().equalsIgnoreCase(DistributionServiceType.ALL_ASSET_DETAILS.getCode())) {
            return this.webClient
                    .get()
                    .uri("/asset/")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }

        /*  calling income stream details service */
        if (nlpResponse.getSER().equalsIgnoreCase(DistributionServiceType.INCOME_STREAM.getCode())) {
            return this.webClient
                    .get()
                    .uri("/income-stream")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }

        return null;
    }
}
