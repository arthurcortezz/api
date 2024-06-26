package com.arthurcortez.javaproject.storage;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class StorageService {

    private final S3Client s3Client;

    public StorageService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file, String fileName) {
        String bucketName = "javaprojectbucket";
        try {
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();
            s3Client.putObject(objectRequest,
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
            ;
            String url = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, "us-east-2",
                    fileName);
            return url;
        } catch (IOException e) {
            throw new RuntimeException("Falha ao fazer upload do arquivo", e);
        }
    }
}