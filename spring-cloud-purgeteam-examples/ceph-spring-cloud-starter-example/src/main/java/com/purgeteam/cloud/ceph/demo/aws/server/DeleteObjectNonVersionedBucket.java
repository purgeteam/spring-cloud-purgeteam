package com.purgeteam.cloud.ceph.demo.aws.server;

/**
 * 使用AWS SDK for Java删除对象
 *
 * @author purgeyao
 * @since 1.0
 */

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.purgeteam.cloud.ceph.demo.aws.Connect;

import java.io.IOException;

public class DeleteObjectNonVersionedBucket {

    public static void main(String[] args) throws IOException {

        String bucketName = "admin-test";
        String keyName = "testkeyname";

        // 对象键名
        String stringObjKeyName = "testkeyname";

        try {
            AmazonS3 s3Client = Connect.amazonS3();

            // 删除对象 (不受版本控制的存储桶)
            s3Client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }
    }
}
