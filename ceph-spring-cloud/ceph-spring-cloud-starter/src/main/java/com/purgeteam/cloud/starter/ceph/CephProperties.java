package com.purgeteam.cloud.starter.ceph;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ceph 配置
 *
 * @author purgeyao
 * @since 1.0
 */
@ConfigurationProperties(prefix = "ceph")
public class CephProperties {

    /**
     * accessKey
     */
    private String accessKey;

    /**
     * secretKey
     */
    private String secretKey;

    /**
     * 端点  如: http://10.16.138.27/
     */
    private String endpoint;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

}
