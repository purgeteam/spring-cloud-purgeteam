package com.purgeteam.cloud.ceph.demo.aws.server;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.purgeteam.cloud.ceph.demo.aws.Connect;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 使用 AWS SDK for Java 上传对象
 *
 * @author purgeyao
 * @since 1.0
 */
public class UploadObject {


    public static void main(String[] args) throws IOException {
        test();
        test1();
    }

    public static void test() {
        AmazonS3 s3Client = Connect.amazonS3();
        // 上传一个文本字符串作为一个新对象。
        s3Client.putObject("admin-test", "testkeyname", "Uploaded String Object");
    }

    public static void test1() throws IOException{
        String bucketName = "admin-test";
        String fileObjKeyName = "file-jpg-test";
        String fileName = "/Volumes/CodeFile/GitHub/purgeteam/spirng-cloud-purgeteam/ceph-spring-cloud/ceph-spring-cloud-demo/src/main/resources/test/01.jpg";

        // 上传一个文件作为一个新的对象与ContentType和标题指定。
        File file = new File(fileName);
        InputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), fileInputStream);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(multipartFile.getContentType());
//            metadata.setContentType("plain/text");
        metadata.setContentLength(multipartFile.getSize());

        AmazonS3 s3Client = Connect.amazonS3();

        try {
            PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, multipartFile.getInputStream(), metadata);
            PutObjectResult putObjectResult = s3Client.putObject(request);
            System.out.println(putObjectResult.getETag());
        } catch (AmazonServiceException e) {
            // 法呼叫成功传输，但Amazon S3无处理
            // 它返回了一个错误响应。
            e.printStackTrace();
        } catch (SdkClientException e) {
            // 无法联系到Amazon S3请求响应或客户端
            // 无法解析来自Amazon S3的响应。
            e.printStackTrace();
        }
    }

}
