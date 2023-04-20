package com.mysite.naverapicsr;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class CSRController {
    @Value("${spring.servlet.multipart.location}")
    String uploadDir;

    @RequestMapping("/upload")
    @ResponseBody
    public String main(MultipartFile uploadfile, CSRApi csr) throws IOException {
        File storedFilename = new File(UUID.randomUUID().toString() + "_" + uploadfile.getOriginalFilename());
        uploadfile.transferTo(storedFilename);
        String imgFile = uploadDir + "\\" + storedFilename.toString();
        System.out.println(imgFile);
        String result = csr.procCSR(imgFile);
        return result;
    }
}
