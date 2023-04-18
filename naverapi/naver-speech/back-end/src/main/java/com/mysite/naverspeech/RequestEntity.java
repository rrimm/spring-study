package com.mysite.naverspeech;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RequestEntity {
    private String language = "ko-KR";
    private String completion = "sync";
    private String callback;
    private Map<String, Object> userdata;
    private Boolean wordAlignment = Boolean.TRUE;
    private Boolean fullText = Boolean.TRUE;
    private List<Boosting> boostings;
    private String forbiddens;
    private Diarization diarization;
}
