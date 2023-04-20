package com.mysite.naverapicsr;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class CSR_Api {
    public static void main(String[] args) {
        String clientId = "0uopuqs5ek";
        String clientSecret = "z4OdmeYERdFIBGU5ZId3zJYLgFXneZqc23RLczbN";

        try {
            String imgFile = "샘플_1.wav";
            File voiceFile = new File(imgFile);
            String language = "Kor";
            String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;// 요청 url 설정
            URL url = new URL(apiURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  // 네이버 API 연결 객체 생성
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/octet-stream");    // 헤더 설정
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);    // 헤더 설정
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientSecret);    // 헤더 설정

            OutputStream outputStream = conn.getOutputStream(); // 네이버 API를 이용해 정보를 전송할 송신 객체 생성
            FileInputStream inputStream = new FileInputStream(voiceFile);   // 음성 파일을 읽기 위한 객체 생성
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {  // 음성파일을 읽어서 buffer에 저장
                outputStream.write(buffer, 0, bytesRead);   // buffer에 저장된 파일의 내용을 네이버 API에게 전송
            }
            outputStream.flush();
            inputStream.close();
            BufferedReader br = null;
            int responseCode = conn.getResponseCode();  // 요청에 대한 응답코드
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                System.out.println("error!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            String inputLine;

            if (br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
            } else {
                System.out.println("error!!!!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
