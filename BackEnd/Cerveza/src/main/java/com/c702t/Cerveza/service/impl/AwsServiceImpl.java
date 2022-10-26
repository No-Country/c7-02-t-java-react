package com.c702t.Cerveza.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.c702t.Cerveza.service.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Service
public class AwsServiceImpl implements AwsService {

    @Value("${spring.aws.s3.bucket}")
    private String bucketName;

    @Value("${spring.aws.s3.endpoint}")
    private String endPoint;

    @Autowired
    private AmazonS3 amazonS3;

    /**
     * metodo que guarda slides(imagenes) en el bucket
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public String uploadFile(MultipartFile file) throws IOException {

        File fileCreated = new File(file.getOriginalFilename());

        try(FileOutputStream outputStream = new FileOutputStream(fileCreated)){

            //en el outputStream escribimos el contenido del multipart
            outputStream.write(file.getBytes());
            // aqui le damos un nombre unico al newFileName
            String newFileName = System.currentTimeMillis() + "_" + fileCreated.getName();
            PutObjectRequest objectRequest = new PutObjectRequest(bucketName, newFileName, fileCreated);
            amazonS3.putObject(objectRequest);
            String fileURL = endPoint + newFileName;
            return fileURL;

        }catch(IOException ex){
            LOGGER.error(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * metodo que obtiene todas las slides del bucket sin diferenciar User, Business, nada
     * @return
     */
    @Override
    public List<String> getObjectOfFromS3() {

        ListObjectsV2Result result = amazonS3.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        List<String> list = objects.stream().map(item -> {
            return  item.getKey();
        }).collect(Collectors.toList());

        return list;
     }

    /**
     * metodo que descarga del bouket las imagenes
     * @param key
     * @return
     */
     @Override
     public InputStream downloadFile(String key){
         S3Object object = amazonS3.getObject(bucketName, key);
         return object.getObjectContent();
     }

}
