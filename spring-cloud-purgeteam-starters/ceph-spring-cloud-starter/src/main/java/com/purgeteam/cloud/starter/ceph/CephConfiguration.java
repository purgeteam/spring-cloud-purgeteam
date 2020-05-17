package com.purgeteam.cloud.starter.ceph;

import com.purgeteam.cloud.starter.ceph.connect.CephConnect;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author purgeyao
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties(CephConnect.class)
public class CephConfiguration {

    @Bean
    public CephConnect cephConnect(CephProperties cephProperties) {
        return new CephConnect(cephProperties);
    }

    @Bean
    public CephClient cephClient(CephConnect cephConnect) {
        return new CephClient(cephConnect);
    }
}
