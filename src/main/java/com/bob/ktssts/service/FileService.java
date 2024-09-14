package com.bob.ktssts.service;

import com.bob.ktssts.config.upload.UploadConfig;
import com.bob.ktssts.mapper.upload.FileDao;
import com.bob.ktssts.model.File;
import com.bob.ktssts.util.upload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.bob.ktssts.util.upload.FileUtils.generateFileName;
import static com.bob.ktssts.util.upload.UploadUtils.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;


/**
 * 文件上传服务
 */
@Service
@ConditionalOnProperty(name = "fileUpload.enable", havingValue = "true")
public class FileService {
    @Autowired
    private FileDao fileDao;


    /**
     * 上传文件
     * @param md5
     * @param file
     */
    public void upload(String name,
                       String md5,
                       MultipartFile file) throws IOException {
        String path = UploadConfig.path + generateFileName();
        FileUtils.write(path, file.getInputStream());
        fileDao.save(new File(name, md5, path, new Date()));
    }

    public String upload(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        // 获取上传保存路径
        String uuid = generateFileName();
        String path = UploadConfig.path + uuid;
        // 获取原始文件名
        String fileName = file.getOriginalFilename();
        FileUtils.write(path, file.getInputStream());
        fileDao.save(new File(fileName, FileUtils.calculateMD5(file), path, new Date()));
        return uuid;
    }

    /**
     * 分块上传文件
     * @param md5
     * @param size
     * @param chunks
     * @param chunk
     * @param file
     * @throws IOException
     */
    public void uploadWithBlock(String name,
                                String md5,
                                Long size,
                                Integer chunks,
                                Integer chunk,
                                MultipartFile file) throws IOException {
        String fileName = getFileName(md5, chunks);
        FileUtils.writeWithBlok(UploadConfig.path + fileName, size, file.getInputStream(), file.getSize(), chunks, chunk);
        addChunk(md5,chunk);
        if (isUploaded(md5)) {
            removeKey(md5);
            fileDao.save(new File(name, md5,UploadConfig.path + fileName, new Date()));
        }
    }

    /**
     * 检查Md5判断文件是否已上传
     * @param md5
     * @return
     */
    public boolean checkMd5(String md5) {
        File file = new File();
        file.setMd5(md5);
        return fileDao.getByFile(file) == null;
    }

    public String getFileOriginName(String nowName) {
        File file = fileDao.getByPath(UploadConfig.path+nowName);
        return fileDao.getByFile(file).getName();
    }
}
