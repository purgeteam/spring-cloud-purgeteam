package com.purgeteam.cloud.starter.ceph.connect;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.purgeteam.cloud.starter.ceph.CephProperties;

/**
 * cphe 连接
 *
 * @author purgeyao
 * @since 1.0
 */
public class CephConnect {

    private CephProperties cephProperties;

    public CephConnect(CephProperties cephProperties) {
        this.cephProperties = cephProperties;
    }

    public AmazonS3 amazonS3() {
        AWSCredentials credentials = new BasicAWSCredentials(cephProperties.getAccessKey(), cephProperties.getSecretKey());
        AmazonS3 conn = new AmazonS3Client(credentials);
        conn.setEndpoint(cephProperties.getEndpoint());
        return conn;
    }
}
