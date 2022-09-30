package com.bravura.finco.service.impls;

import com.bravura.finco.constant.ProductType;
import com.bravura.finco.model.FincoResponse;
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
    public FincoResponse getProduct(FincoResponse fincoResponse, Boolean isAlexa) {
        if(fincoResponse.getNlpResponse().getPROD().equalsIgnoreCase(ProductType.DISTRIBUTIONS.getCode())) {
           return distributionService.callDistributionProduct(fincoResponse, isAlexa);
        }
        if(fincoResponse.getNlpResponse().getPROD().equalsIgnoreCase(ProductType.SONATA.getCode())) {
            return sbsService.callSonataProduct(fincoResponse, isAlexa);
        }

        return FincoResponse.builder().build();
    }
}
