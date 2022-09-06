package com.finCo.hub.service;

import com.google.gson.JsonObject;
import lombok.NonNull;

public interface NlpService {

    Object getNlp(@NonNull String serchString);
}
