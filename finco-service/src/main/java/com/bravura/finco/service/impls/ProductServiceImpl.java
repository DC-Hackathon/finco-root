package com.bravura.finco.service.impls;

import com.bravura.finco.constant.ProductType;
import com.bravura.finco.model.GetClientResponse;
import com.bravura.finco.model.NLPResponse;
import com.bravura.finco.service.DistributionService;
import com.bravura.finco.service.NucleusService;
import com.bravura.finco.service.ProductService;
import com.bravura.finco.service.SonataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private DistributionService distributionService;
    @Autowired
    private SonataService sbsService;
    @Autowired
    private NucleusService nucleusService;
    @Override
    public Object getProduct(NLPResponse nlpResponse) {
        if(nlpResponse.getPROD().equalsIgnoreCase(ProductType.DISTRIBUTIONS.getCode())) {
           return distributionService.callDistributionProduct(nlpResponse);
        }
        if(nlpResponse.getPROD().equalsIgnoreCase(ProductType.SONATA.getCode())) {
            return sbsService.callSonataProduct(nlpResponse);
        }
        if(nlpResponse.getPROD().equalsIgnoreCase(ProductType.NUCLEUS.getCode())) {
            return nucleusService.callNucleusProduct(nlpResponse);
        }

        return null;
    }
}
