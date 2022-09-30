package com.bravura.finco.service;

import com.bravura.finco.model.FincoResponse;
import com.bravura.finco.model.NLPResponse;

public interface DistributionService {
    FincoResponse callDistributionProduct(FincoResponse fincoResponse, Boolean isAlexa);
}
