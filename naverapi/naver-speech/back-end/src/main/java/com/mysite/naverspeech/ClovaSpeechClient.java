package com.mysite.naverspeech;

import com.google.gson.Gson;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;


public class ClovaSpeechClient {
    private static final String SECRET = "f41a6049c2cf43a38e2ab61d21a3cd76";
    private static final String INVOKE_URL = "https://clovaspeech-gw.ncloud.com/external/v1/4926/4f0a4fdbcb390a2bfd89375df01510e3ca1a00345726f9e2d055c51eceb88915";
    private CloseableHttpClient httpClient = HttpClients.createDefault();   // http client 객체 생성
    private Gson gson = new Gson();

    private static final Header[] HEADERS = new Header[]{
            new BasicHeader("Accept", "application/json"),
            new BasicHeader("X-CLOVASPEECH-API-KEY", SECRET),
    };

    public String upload(File file, RequestEntity nestRequestEntity) {
        // post 메서드와 URL 설정
        HttpPost httpPost = new HttpPost(INVOKE_URL + "/recognizer/upload");
        httpPost.setHeaders(HEADERS);
        HttpEntity httpEntity = MultipartEntityBuilder.create()
                .addTextBody("params", gson.toJson(nestRequestEntity), ContentType.APPLICATION_JSON)
                .addBinaryBody("media", file, ContentType.MULTIPART_FORM_DATA, file.getName())
                .build();
        httpPost.setEntity(httpEntity);    // 요청 데이터 정보를 전달
        return execute(httpPost);
    }
    private String execute(HttpPost httpPost) {
        // httpClient.execute(): http 서버에 request 메세지를 전송하고 response 메세지를 전달받기 위해 httpClient 객체와 httpPost 메서드 객체를 사용하여 http 통신을 실행
        try (final CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
            final HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
