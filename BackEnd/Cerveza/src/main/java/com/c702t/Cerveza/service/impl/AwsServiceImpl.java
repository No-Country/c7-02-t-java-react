package com.c702t.Cerveza.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.c702t.Cerveza.service.AwsService;
import com.c702t.Cerveza.utils.AwsUtils;
import com.c702t.Cerveza.utils.MultiPartFileClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class AwsServiceImpl implements AwsService {

    @Value("${spring.aws.s3.bucket}")
    private String bucketName;

    @Value("${spring.aws.s3.endpoint}")
    private String endPoint;

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private AwsUtils awsUtils;

    private void uploadFile2Asw3 (String fileName, File file){

        this.amazonS3.putObject(new PutObjectRequest(bucketName,fileName,file)
                .withCannedAcl(CannedAccessControlList.PublicRead));

    }

    private String uploadFile(MultipartFile multipartFile) throws IOException {

        File fileCreated = awsUtils.convertMultiPartToFile(multipartFile);
        String fileName = multipartFile.getOriginalFilename();
        uploadFile2Asw3(fileName,fileCreated);
        fileCreated.delete();
        String fileURL = endPoint + "/" + bucketName + "/" + fileName;
        return fileURL;

    }

    @Override
    public String uploadFileFromBase64(String base64) throws IOException {

        if (base64  == null)
            return null;

        if (base64.contains("data:image/")) {
            String[] parts = base64.split(",");
            String header = parts[0];
            String contents = parts[1];
            MultipartFile multipartFile = new MultiPartFileClassUtils(header, contents);
            return uploadFile(multipartFile);
        }
        else
            return endPoint + "/" + bucketName + "/" + base64;
    }

}