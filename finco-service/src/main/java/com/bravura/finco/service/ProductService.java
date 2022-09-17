package com.bravura.finco.service;

import com.bravura.finco.model.FincoResponse;
import com.bravura.finco.model.NLPResponse;

public interface ProductService {

    FincoResponse getProduct(NLPResponse nlpResponse);
}
