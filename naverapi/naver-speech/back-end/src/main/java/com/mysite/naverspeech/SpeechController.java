package com.mysite.naverspeech;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class SpeechController {

    @Value("${spring.servlet.multipart.location}")
    String uploadDir;

    @RequestMapping("/upload")
    @ResponseBody
    public String main(MultipartFile uploadfile, ClovaSpeechClient clovaSpeechClient, RequestEntity requestEntity) throws IOException {
        File storedFilename = new File(uploadfile.getOriginalFilename());
        uploadfile.transferTo(storedFilename);
        String result = clovaSpeechClient.upload(new File(uploadDir + "/" + storedFilename), requestEntity);
        return result;
    }
}
