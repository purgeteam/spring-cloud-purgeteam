package com.purgeteam.cloud.ceph.demo.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

/**
 * @author purgeyao
 * @since 1.0
 */
public class Account {

    public static AWSCredentials awsCredentials(){
        // admin
        String accessKey = "ZMFODC0PP9JVFFAHH045";
        String secretKey = "2fuedC6aaGbAjnPhp4I8aYE2RM8dQTjnw7FvDiZb";

        // liuyz
//        String accessKey = "EJ178UF21W3IGYUVYQFD";
//        String secretKey = "kM1OloEDFYUuIKaAl3p8UC7Du4q7oNR7fpyhcd0Y";

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return credentials;
    }
}
