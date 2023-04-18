package com.mysite.naverocr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class OCRController {
    @RequestMapping("/upload")
    @ResponseBody
    public String main(MultipartFile uploadfile, OCRApiReact ocr) {
        System.out.println("uploadfile.getOriginalFilename() : " + uploadfile.getOriginalFilename());
        String result = ocr.processOCR(uploadfile.getOriginalFilename());
        return result;
    }
}
