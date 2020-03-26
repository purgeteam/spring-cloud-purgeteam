package com.purgeteam.cloud.ceph.demo.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

/**
 * @author purgeyao
 * @since 1.0
 */
public class Connect {

    public static AmazonS3 amazonS3() {
        AmazonS3 conn = new AmazonS3Client(Account.awsCredentials());
        conn.setEndpoint("http://10.16.138.27/");
        return conn;
    }
}
