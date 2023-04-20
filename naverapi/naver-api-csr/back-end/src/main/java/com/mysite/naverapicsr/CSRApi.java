package com.mysite.naverapicsr;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class CSRApi {
    public String procCSR(String imgFile) {

        String clientId = "0uopuqs5ek";
        String clientSecret = "z4OdmeYERdFIBGU5ZId3zJYLgFXneZqc23RLczbN";

        StringBuffer response = new StringBuffer();
        try {
            File voiceFile = new File(imgFile);
            String language = "Kor";    // 언어 코드
            String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;  // 요청 url 설정
            URL url = new URL(apiURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/octet-stream");
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            conn.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(voiceFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            BufferedReader br = null;
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                System.out.println("error!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            String inputLine;

            if (br != null) {
                response = new StringBuffer();
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

        return response.toString();
    }
}
