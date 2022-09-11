package com.bravura.finco.service;

import com.bravura.finco.model.NlpResponse;

public interface ProductService {

    <T> T getProduct(NlpResponse nlpResponse);
}
