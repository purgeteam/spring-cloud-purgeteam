package com.purgeteam.cloud.ceph.demo;

import org.twonote.rgwadmin4j.RgwAdmin;
import org.twonote.rgwadmin4j.RgwAdminBuilder;
import org.twonote.rgwadmin4j.model.UsageInfo;
import org.twonote.rgwadmin4j.model.User;

import java.util.Optional;

/**
 * @author purgeyao
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) {

        RgwAdmin rgwAdmin =
                new RgwAdminBuilder()
                        .accessKey("ZMFODC0PP9JVFFAHH045")
                        .secretKey("2fuedC6aaGbAjnPhp4I8aYE2RM8dQTjnw7FvDiZb")
                        .endpoint("http://10.16.134.26:8443/")
                        .build();
        Optional<UsageInfo> usage = rgwAdmin.getUsage();
        Optional<User> userInfo = rgwAdmin.getUserInfo("liuyz");
        System.out.println("");
    }

}
