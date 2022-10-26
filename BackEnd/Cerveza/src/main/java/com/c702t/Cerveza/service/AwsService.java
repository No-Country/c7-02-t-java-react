package com.c702t.Cerveza.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface AwsService {
    public String uploadFile(MultipartFile multipartFile) throws IOException;
    public List<String> getObjectOfFromS3();
    public InputStream downloadFile(String key);

}