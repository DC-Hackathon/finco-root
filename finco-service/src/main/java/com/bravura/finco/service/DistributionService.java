package com.bravura.finco.service;

import com.bravura.finco.model.NlpResponse;

public interface DistributionService {
    <T> T callDistributionProduct(NlpResponse nlpResponse);
}
