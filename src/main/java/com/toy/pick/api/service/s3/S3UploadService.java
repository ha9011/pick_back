package com.toy.pick.api.service.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3UploadService {

    private final AmazonS3 amazonS3;


    @Value("${cloud.aws.s3.bucket.name}")
    private String bucket;
    public String saveFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();

        multipartFile.getContentType();
        System.out.println("originalFilename : "+ originalFilename);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());
        //metadata.setContentDisposition("attachment; filename='"+originalFilename+"'");
        System.out.println("bucket : " + bucket);

        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);

        String string = amazonS3.getUrl(bucket, originalFilename).toString();
        System.out.println(string);
        String encode = URLEncoder.encode(string, Charsets.UTF_8);
        System.out.println(encode);

        // S3 업로드 확인
        // TODO 파일명 등 커스텀 해보자.
        // UUID로 해볼까, 랜덤값으로 파일명할까.
        // 폴더명으로 관리하는게 좋을거 같음.
        return null;
    }


}
