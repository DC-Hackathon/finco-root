package com.bravura.finco.service;

import com.bravura.finco.model.asset.FincoResponse;
import lombok.NonNull;

public interface NLPService {

    FincoResponse getNlp(@NonNull String serchString);
}
