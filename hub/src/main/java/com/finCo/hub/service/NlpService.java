package com.finCo.hub.service;

import com.google.gson.JsonObject;
import lombok.NonNull;

public interface NlpService {

    JsonObject getNlp(@NonNull String serchString);
}
