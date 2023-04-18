package com.mysite.naverspeech;

import java.io.File;

public class MainTest {
    public static void main(String[] args) {
        ClovaSpeechClient clovaSpeechClient = new ClovaSpeechClient();
        RequestEntity requestEntity = new RequestEntity();
        String result = clovaSpeechClient.upload(new File("샘플_1.wav"), requestEntity);
        System.out.println(result);
    }
}
