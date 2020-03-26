package com.purgeteam.cloud.starter.ceph;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.purgeteam.cloud.starter.ceph.connect.CephConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * ceph 客户端
 *
 * @author purgeyao
 * @since 1.0
 */
public class CephClient {

    private static final Logger log = LoggerFactory.getLogger(CephClient.class);

    private CephConnect cephConnect;

    public CephClient(CephConnect cephConnect) {
        this.cephConnect = cephConnect;
    }

    /**
     * 上传文件
     *
     * @param bucketName 手动在AWS S3中创建的桶名称
     * @param keyName    希望这个文件上传后的key，需要注意的，这个key不要重复，否则会覆盖掉旧的文件
     * @param file       待上传的本地
     * @return 是否成功
     */
    public Boolean putObject(String bucketName, String keyName, MultipartFile file) {
        AmazonS3 s3Client = cephConnect.amazonS3();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        try {
            PutObjectRequest request = new PutObjectRequest(bucketName, keyName, file.getInputStream(), metadata);
            s3Client.putObject(request);
        } catch (AmazonServiceException ase) {
            log.warn("Caught an AmazonServiceException, which means your request made it to Amazon S3, but was rejected with an error response for some reason.");
            log.warn(ase.getMessage());
            return false;
        } catch (AmazonClientException ace) {
            log.warn("Caught an AmazonClientException, which means the client encountered an internal error while trying to communicate with S3, such as not being able to access the network.");
            log.warn("Error Message: " + ace.getMessage());
            return false;
        } catch (IOException ioe) {
            log.warn("Caught an IOException: " + ioe.getMessage());
            return false;
        }
        return true;
    }

}
