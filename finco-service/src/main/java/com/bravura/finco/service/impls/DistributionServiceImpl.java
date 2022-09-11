package com.bravura.finco.service.impls;

import com.bravura.finco.constant.DistributionServiceType;
import com.bravura.finco.model.NlpResponse;
import com.bravura.finco.model.asset.Asset;
import com.bravura.finco.service.DistributionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DistributionServiceImpl implements DistributionService {

    private static final Logger log = LoggerFactory.getLogger(DistributionServiceImpl.class);

    @Qualifier("dist")
    @Autowired
    private final WebClient webClient;

    public DistributionServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public <T> T callDistributionProduct(NlpResponse nlpResponse) {

        /* Now getting services from nlpResponse
        *  Map it with correct service type
        * */

        if(nlpResponse.getSER().equalsIgnoreCase(DistributionServiceType.ASSET_DETAILS.getCode())) {
            Asset assetDataBean = this.webClient
                    .get()
                    .uri("/asset/GB00BFBFYK62")
                    .retrieve()
                    .bodyToMono(Asset.class)
                    .block();
            return (T) assetDataBean;
        }

        return null;
    }
}
