package com.example.doctor_clinics.services.implementations;

import com.example.doctor_clinics.components.Minio;
import io.minio.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MinioService {
    private final Minio minio;
    @Value("${minio.bucket}")
    private String bucket;


    public void uploadImage(MultipartFile file, String path) {
        try {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(bucket)
                .object(path)
                .contentType(file.getContentType())
                .stream(file.getInputStream(), file.getSize(), -1)
                .build();

            minio.getClient().putObject(putObjectArgs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void removeImage(String imageUrl) {
        StatObjectArgs statObjectArgs = StatObjectArgs.builder()
            .bucket(bucket)
            .object(imageUrl)
            .build();

        try {
            StatObjectResponse statObjectResponse = minio.getClient().statObject(statObjectArgs);
            if (statObjectResponse != null) {
                RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(imageUrl)
                    .build();

                minio.getClient().removeObject(removeObjectArgs);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    private void init() {
        boolean exists;
        try {
            exists = minio.getClient().bucketExists(BucketExistsArgs.builder()
                .bucket(bucket).build());
            if (!exists) {
                minio.getClient().makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
