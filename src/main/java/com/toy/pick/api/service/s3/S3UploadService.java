package com.toy.pick.api.service.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.toy.pick.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLDecoder;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3UploadService {

    private final AmazonS3 amazonS3;


    @Value("${cloud.aws.s3.bucket.name}")
    private String bucket;
    @Value("${cloud.aws.s3.path.basic}")
    private String basicPath;
    public String saveFile(MultipartFile multipartFile, Long id)  {

        try{
            multipartFile.getContentType();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(multipartFile.getSize());
            metadata.setContentType(multipartFile.getContentType());

            // UUID 생성
            String uniqueFileName = UUID.randomUUID().toString();

            // buckit + 환경명 + place(Pk)
            String basicBuckitDir = bucket + "/" + basicPath + "/" + id;

            // TODO 파일명 고민해보기 curr : uuid + 기본파일명
            String fileName = uniqueFileName + "-" + multipartFile.getOriginalFilename();


            // 저장
            amazonS3.putObject(basicBuckitDir, fileName, multipartFile.getInputStream(), metadata);

            // 경로 호출
            String string = amazonS3.getUrl(bucket, fileName).toString();

            // 한글, 띄어쓰기 경우 파일명깨짐 -> 디코딩하기
            String decodedFileName = URLDecoder.decode(string, "UTF-8");
            log.info(decodedFileName);
            return decodedFileName;
        }catch (Exception e){
            throw new CustomException("파일 업로드에 실패했습니다.");
        }
    }
}
