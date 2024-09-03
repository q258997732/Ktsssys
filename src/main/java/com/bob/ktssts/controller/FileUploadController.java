package com.bob.ktssts.controller;

import com.bob.ktssts.service.FileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class FileUploadController {

    private static final Logger LOGGER = LogManager.getLogger(FileUploadController.class);


    @Autowired
    private FileService fileService;

    @PostMapping("/file")
    public void upload(String name,
                       String md5,
                       MultipartFile file) throws IOException {
        fileService.upload(name, md5,file);
        LOGGER.info("文件上传成功");
    }

    @PostMapping("/test")
    public void test(@RequestPart("file") MultipartFile file) throws IOException {
        LOGGER.info("test");
        fileService.upload("test", "test",file);
    }
}
