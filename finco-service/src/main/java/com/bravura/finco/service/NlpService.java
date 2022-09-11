package com.bravura.finco.service;

import lombok.NonNull;

public interface NlpService {

    <T> T getNlp(@NonNull String serchString);
}
