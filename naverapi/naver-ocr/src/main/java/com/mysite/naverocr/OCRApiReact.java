package com.mysite.naverocr;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OCRApiReact {
    public String processOCR(String img) {
        String apiURL = "https://dv5yppx7ev.apigw.ntruss.com/custom/v1/21775/66a71440944d02a71d210ba1e935596256e6570c5f6d4c69657cfd8676743e5a/infer";
        String secretKey = "VWpmdlpKc3ZnaFhPSUZVdXdXbllERXNsdGZzeFdYbmw=";
        String imageFile = img;

        String responseJson = "";
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setReadTimeout(30000);
            con.setRequestMethod("GET");
            String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-OCR-SECRET", secretKey);
            JSONObject json = new JSONObject();
            json.put("version", "V2");
            json.put("requestId", UUID.randomUUID().toString());
            json.put("timestamp", System.currentTimeMillis());
            JSONObject image = new JSONObject();
            image.put("format", "jpg");
            image.put("name", "demo");
            JSONArray images = new JSONArray();
            images.put(image);
            json.put("images", images);
            String postParams = json.toString();
            con.connect();
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            long start = System.currentTimeMillis();
            File file = new File(imageFile);
            this.writeMultiPart(wr, postParams, file, boundary);
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            StringBuffer response = new StringBuffer();

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();

            JSONObject responseObj = new JSONObject(response.toString());
            Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
            responseJson = gson.toJson(responseObj);
            System.out.println(responseJson);

            responseDataPrint(response.toString());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return responseJson;
    }

    private void responseDataPrint(String response) {
        JSONObject jObject = new JSONObject(response);
        String version = jObject.getString("version");
        String requestId = jObject.getString("requestId");
        Date date = new Date(jObject.getLong("timestamp"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String update_date = sdf.format(date);
        System.out.println("------------------------------------------");
        System.out.println("version : " + version);
        System.out.println("requestId : " + requestId);
        System.out.println("timestamp : " + update_date);
        JSONArray images = (JSONArray) jObject.get("images");
        JSONObject imagesObj = (JSONObject) images.get(0);
        JSONObject title = (JSONObject) imagesObj.get("title");
        JSONArray fields = (JSONArray) imagesObj.get("fields");

        for (int i = 0; i < fields.length(); ++i) {
            JSONObject fieldsObj = (JSONObject) fields.get(i);
            String name = fieldsObj.getString("name");
            String inferText = fieldsObj.getString("inferText");
            System.out.println("------------------------------------------");
            System.out.println("name : " + name);
            System.out.println("inferText : " + inferText);
        }

    }

    private void writeMultiPart(OutputStream out, String jsonMessage, File file, String boundary) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("--").append(boundary).append("\r\n");
        sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n");
        sb.append(jsonMessage);
        sb.append("\r\n");
        out.write(sb.toString().getBytes("UTF-8"));
        out.flush();
        if (file != null && file.isFile()) {
            out.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
            StringBuilder fileString = new StringBuilder();
            fileString.append("Content-Disposition:form-data; name=\"file\"; filename=");
            fileString.append("\"" + file.getName() + "\"\r\n");
            fileString.append("Content-Type: application/octet-stream\r\n\r\n");
            out.write(fileString.toString().getBytes("UTF-8"));
            out.flush();
            Throwable var7 = null;
            Object var8 = null;

            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[8192];
                int count;
                while ((count = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                }
                out.write("\r\n".getBytes());
            }
            out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
        }
        out.flush();
    }
}
