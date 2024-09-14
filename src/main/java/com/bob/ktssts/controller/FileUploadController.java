package com.bob.ktssts.controller;

import com.bob.ktssts.entity.response.ErrorResponseBean;
import com.bob.ktssts.entity.response.ResponseBean;
import com.bob.ktssts.entity.response.SuccessResponseBean;
import com.bob.ktssts.service.FileService;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
@ConditionalOnProperty(name = "fileUpload.enable", havingValue = "true")
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

    @PostMapping("/uploadFile")
    public ResponseEntity<ResponseBean<Object>> uploadFile(@RequestPart("file") MultipartFile file) throws IOException {
        String fileName;
        try {
            fileName = fileService.upload(file);
        }catch (Exception e){
            LOGGER.error("文件上传失败",e);
            return ResponseEntity.badRequest().body(new ErrorResponseBean<>("文件上传失败"));
        }
        return ResponseEntity.ok(new SuccessResponseBean<>("文件上传成功",fileName));
    }

    @PostMapping("/uploadFiles")
    public ResponseEntity<ResponseBean<Object>> uploadFiles(@RequestPart("files") List<MultipartFile> files) throws IOException {
//        LOGGER.info("文件上传开始{}",files.getSize());
        List<String> uploadSuccessList = new ArrayList<>();
        List<String> uploadFailList = new ArrayList<>();
        StringBuilder res = new StringBuilder();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            try {
                String filePath = fileService.upload(file);
                uploadSuccessList.add(fileName);
                res.append(filePath);
                res.append(";");
            } catch (Exception e) {
                LOGGER.error("文件上传失败", e);
                uploadFailList.add(fileName);
            }
        }
        return ResponseEntity.ok(new SuccessResponseBean<>(String.format("上传成功：%s，上传失败：%s", uploadSuccessList, uploadFailList),res));
    }

    @PostMapping("/test2")
    public void test2(){
        LOGGER.info("test2");
    }

}
