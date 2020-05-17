package com.purgeteam.cloud.ceph.demo.aws.server;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.amazonaws.services.s3.model.S3Object;
import com.purgeteam.cloud.ceph.demo.aws.Connect;

import java.io.*;

/**
 * 使用 AWS SDK for Java 获取对象
 *
 * @author purgeyao
 * @since 1.0
 */
public class GetObject {

    public static void main(String[] args) throws IOException {

        String bucketName = "admin-test";
        String key = "testkeyname";
//        key = "file-jpg-test";


        S3Object fullObject = null, objectPortion = null, headerOverrideObject = null;
        try {
            AmazonS3 s3Client = Connect.amazonS3();

            // 获取一个对象并打印其内容。
            System.out.println("下载一个对象");
            fullObject = s3Client.getObject(new GetObjectRequest(bucketName, key));
            System.out.println("内容类型 " + fullObject.getObjectMetadata().getContentType());
            System.out.println("内容 ");
            displayTextInputStream(fullObject.getObjectContent());

            writeToLocal("/Users/purgeyao/Downloads/1.txt", fullObject.getObjectContent());

        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        } finally {
            // To ensure that the network connection doesn't remain open, close any open input streams.
            if (fullObject != null) {
                fullObject.close();
            }
            if (objectPortion != null) {
                objectPortion.close();
            }
            if (headerOverrideObject != null) {
                headerOverrideObject.close();
            }
        }
    }

    private static void displayTextInputStream(InputStream input) throws IOException {
        // 读取文本输入流，每次一行，并显示每一行。
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println();
    }

    /**
     * 将InputStream写入本地文件
     *
     * @param destination 写入本地目录
     * @param input       输入流
     * @throws IOException
     */
    private static void writeToLocal(String destination, InputStream input)
            throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(destination);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        input.close();
    }
}
