package com.purgeteam.cloud.ceph.demo.aws;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;

import java.io.File;
import java.util.List;

/**
 * @author purgeyao
 * @since 1.0
 */
public class S3Util {

    private static AmazonS3 s3;

    static {
        s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.CN_NORTH_1).build();
    }

    private S3Util() {
    }

    /*
     * Create a new S3 bucket - Amazon S3 bucket names are globally unique
     */
    public static Bucket createBucket(String bucketName) {
        return s3.createBucket(bucketName);
    }

    /*
     * List the buckets in your account
     */
    public static List<Bucket> listBuckets() {
        return s3.listBuckets();
    }

    /*
     * List objects in your bucket
     */
    public static ObjectListing listObjects(String bucketName) {
        return s3.listObjects(bucketName);
    }

    /*
     * List objects in your bucket by prefix
     */
    public static ObjectListing listObjects(String bucketName, String prefix) {
        return s3.listObjects(bucketName, prefix);
    }

    /*
     * Upload an object to your bucket
     */
    public static PutObjectResult putObject(String bucketName, String key, File file) {
        return s3.putObject(bucketName, key, file);
    }

    /*
     * Download an object - When you download an object, you get all of the object's metadata and a stream from which to read the contents.
     * It's important to read the contents of the stream as quickly as possibly since the data is streamed directly from Amazon S3 and your
     * network connection will remain open until you read all the data or close the input stream.
     */
    public static S3Object get(String bucketName, String key) {
        return s3.getObject(bucketName, key);
    }

    /*
     * Delete an object - Unless versioning has been turned on for your bucket, there is no way to undelete an object, so use caution when deleting objects.
     */
    public static void deleteObject(String bucketName, String key) {
        s3.deleteObject(bucketName, key);
    }

    /*
     * Delete a bucket - A bucket must be completely empty before it can be deleted, so remember to delete any objects from your buckets before
     * you try to delete them.
     */
    public static void deleteBucket(String bucketName) {
        s3.deleteBucket(bucketName);
    }
}
