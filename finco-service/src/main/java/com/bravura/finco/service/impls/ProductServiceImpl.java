package com.bravura.finco.service.impls;

import com.bravura.finco.constant.ProductType;
import com.bravura.finco.model.NlpResponse;
import com.bravura.finco.service.DistributionService;
import com.bravura.finco.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private DistributionService distributionService;
    @Override
    public <T> T getProduct(NlpResponse nlpResponse) {
        if(nlpResponse.getPROD().equalsIgnoreCase(ProductType.DISTRIBUTIONS.getCode())) {
           return distributionService.callDistributionProduct(nlpResponse);
        }
        return null;
    }

    private void modifyingNlpResponseByDecidingProductType(NlpResponse nlpResponse) {
    }

}
