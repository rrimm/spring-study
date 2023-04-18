package com.mysite.chatbot01;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@Controller
public class ChatController {
    private static String secretKey = "Q2dMVmlRTWhZTkVyaExUcVdEZ3VvSGtLbUZOT0hZQ3E=";
    private static String apiUrl = "https://5vrsemy1x2.apigw.ntruss.com/custom/v1/10031/233d95b2352d5821bd9d40a36339ffc930ef124e88bc8d79d2e64c8417a173d4";

    @MessageMapping("/sendMessage") // 어노테이션에 발행하는 경로를 @SendTo 어노테이션에 구독 경로를 작성한다.
    @SendTo("/topic/public")    // 서버에서 클라이언트로 수신한 메시지를 보낼 때 사용할 url
    public String sendMessage(@Payload String chatMessage) throws IOException { //@Payload: 요청 메세지 내용 중 body의 실제 데이터를 의미
        URL url = new URL(apiUrl);  // 챗봇의 url
        String message = getReqMessage(chatMessage);
        String encodeBase64String = makeSignature(message, secretKey);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();   // 연결객체를 생성하며 연결 요청
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json;UTF-8");
        con.setRequestProperty("X-NCP-CHATBOT_SIGNATURE", encodeBase64String);

        con.setDoOutput(true);  // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());  // con.getOutputStream(): 챗봇에게 메세지를 전송할 송신 객체 생성

        wr.write(message.getBytes("UTF-8"));    // 실제 전송하는 부분
        wr.flush(); // 버퍼를 비우는 작업
        wr.close(); // 버퍼를 닫는 작업
        int responseCode = con.getResponseCode();

        BufferedReader in;
        if (responseCode == 200) {  // 정상 호출
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));  // con.getInputStream(): 챗봇이 응답하는 메세지를 수신할 객체 생성
            String decodedString;
            String jsonString = "";
            while ((decodedString = in.readLine()) != null) {   // in.readLine() : 응답 메세지 수신
                jsonString = decodedString;
            }
            System.out.println("##########################");
            System.out.println(jsonString);
            System.out.println("##########################");

            org.json.simple.parser.JSONParser jsonparser = new JSONParser();
            try {
                JSONObject json = (JSONObject)jsonparser.parse(jsonString);
                JSONArray bubblesArray = (JSONArray)json.get("bubbles");
                JSONObject bubbles = (JSONObject)bubblesArray.get(0);
                System.out.println("bubbles : " + bubbles);
                JSONObject data = (JSONObject)bubbles.get("data");
                System.out.println("data : " + data);
                String description = "";
                description = (String)data.get("description");
                chatMessage = description;
                System.out.println("chatMessage : " + chatMessage);
            } catch (Exception e) {
                System.out.println("error");
                e.printStackTrace();
            }

            in.close();
        } else { // 에러 발생
            chatMessage = con.getResponseMessage();
        }

        return chatMessage;
    }

    public String makeSignature(String message, String secretKey) { // 메세지를 전송하기 위해 base64
        String encodeBase64String = "";

        try {
            byte[] secrete_key_bytes = secretKey.getBytes("UTF-8");

            SecretKeySpec signingKey = new SecretKeySpec(secrete_key_bytes, "HmacSHA256");  // SecretKeySpec 클래스를 사용한 키 생성
            Mac mac = Mac.getInstance("HmacSHA256");    // 지정된 MAC 알고리즘을 구현하는 Mac 객체 생성
            mac.init(signingKey);   // 키를 사용해 이 Mac 객체를 초기화
            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
            encodeBase64String = Base64.encodeBase64String(rawHmac);    // Base 64 Encode to String
            return encodeBase64String;

        } catch (Exception e) {
            System.out.println("makeSignature : " + e);
        }

        return encodeBase64String;
    }

    public String getReqMessage(String voiceMessage) {
        String requestBody = "";

        try {
            JSONObject obj = new JSONObject();

            long timestamp = new Date().getTime();

            System.out.println("##" + timestamp);

            obj.put("version", "v2");
            obj.put("userId", "zoca"); // 사용자를 식별할 아이디 입력
            obj.put("timestamp", timestamp);

            JSONObject bubbles_obj = new JSONObject();
            bubbles_obj.put("type", "text");

            JSONObject data_obj = new JSONObject();
            data_obj.put("description", voiceMessage);
            bubbles_obj.put("data", data_obj);

            JSONArray bubbles_array = new JSONArray();
            bubbles_array.add(bubbles_obj);

            obj.put("bubbles", bubbles_array);
            obj.put("event", "send");

            requestBody = obj.toString();
            System.out.println("requestBody => " + requestBody);

        } catch (Exception e) {
            System.out.println("## Exception : " + e);
        }

        return requestBody;
    }
}
