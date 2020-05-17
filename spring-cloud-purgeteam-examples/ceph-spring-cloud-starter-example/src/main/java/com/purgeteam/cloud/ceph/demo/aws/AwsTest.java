package com.purgeteam.cloud.ceph.demo.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.util.StringUtils;

import java.util.List;

/**
 * @author purgeyao
 * @since 1.0
 */
public class AwsTest {

    public static void main(String[] args) {

        AmazonS3 conn = Connect.amazonS3();

        // 列出用户的所有 BUCKET
        List<Bucket> buckets = conn.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(bucket.getName() + "\t" + StringUtils.fromDate(bucket.getCreationDate()));
        }

        // 新建一个 BUCKET
        Bucket bucket = conn.createBucket("my-new-bucket");

    }
}
