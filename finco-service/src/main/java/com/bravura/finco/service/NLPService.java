package com.bravura.finco.service;

import com.bravura.finco.model.FincoResponse;
import lombok.NonNull;

public interface NLPService {

    FincoResponse getNlp(@NonNull String serchString);
}
