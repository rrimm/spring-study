package com.mysite.naverspeech;

import lombok.Data;

@Data
public class Diarization {
    private Boolean enable = Boolean.FALSE;
    private Integer speakerCountMin;
    private Integer speakerCountMax;
}
