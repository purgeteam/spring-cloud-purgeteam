package com.purgeteam.cloud.ceph.demo.aws.server;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.purgeteam.cloud.ceph.demo.aws.Connect;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * 使用预签名 URL 上传对象（AWS SDK for Java）
 *
 * @author purgeyao
 * @since 1.0
 */
public class GeneratePresignedUrlAndUploadObject {

    public static void main(String[] args) throws IOException {

        String bucketName = "admin-test";
        String objectKey = "file-jpg-test";
        objectKey = "testkeyname";
        String url = generatePresignedUrl(bucketName, objectKey, 6000);
        System.out.println(url);
    }

    public void test(String bucketName, String objectKey) throws IOException {
        try {
            AmazonS3 s3Client = Connect.amazonS3();

            // 将预签名URL设置为一小时后过期。
            Date expiration = new Date();
            long expTimeMillis = expiration.getTime();
            expTimeMillis += 1000 * 60 * 60;
            expiration.setTime(expTimeMillis);

            // 生成预签名的URL。
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, objectKey)
                    .withMethod(HttpMethod.PUT)
                    .withExpiration(expiration);

            URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
            System.out.println("生成的签名的URL: " + url.toString());

            // 创建连接并使用它使用预签名的URL上传新对象。
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write("此文本通过预签名URL作为对象上传。");
            out.close();

            // 检查HTTP响应代码。要完成上传并使对象可用，
            // 您必须以某种方式与连接对象交互。
            System.out.println("HTTP响应代码: " + connection.getResponseCode());

            // 检查以确保对象已成功上传。
            S3Object object = s3Client.getObject(bucketName, objectKey);
            System.out.println("Object " + object.getKey() + " created in bucket " + object.getBucketName());
        } catch (AmazonServiceException e) {
            // 呼叫成功传输，但Amazon S3无法处理
            // 它返回了一个错误响应。
            e.printStackTrace();
        } catch (SdkClientException e) {
            // 无法联系到Amazon S3请求响应或客户端
            // 无法解析来自Amazon S3的响应。
            e.printStackTrace();
        }
    }

    public static String generatePresignedUrl(String bucketName, String key, int minutes) {
        // 设置过期日期
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * minutes;
        expiration.setTime(expTimeMillis);

        // Generate the presigned URL.
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, key).withMethod(HttpMethod.GET).withExpiration(expiration);
        AmazonS3 s3Client = Connect.amazonS3();
        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

        // 创建连接并使用它使用预签名的URL上传新对象。
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write("This text uploaded as an object via presigned URL.");
            out.close();

            // 检查HTTP响应代码。要完成上传并使对象可用，
            // 您必须以某种方式与连接对象交互。
            System.out.println("HTTP响应代码: " + connection.getResponseCode());
            System.out.println("HTTP响应msg: " + connection.getResponseMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return url.toString();
    }


}
