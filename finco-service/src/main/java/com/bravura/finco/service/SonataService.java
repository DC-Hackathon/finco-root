package com.bravura.finco.service;

import com.bravura.finco.model.FincoResponse;
import com.bravura.finco.model.NLPResponse;

public interface SonataService {
    < T extends Object> T callSonataProduct(FincoResponse fincoResponse, Boolean isAlexa);
}
