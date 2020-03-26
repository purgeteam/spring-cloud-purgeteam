package com.purgeteam.cloud.ceph.demo;

import com.ceph.rados.Rados;
import com.ceph.rados.exceptions.RadosException;

import java.io.File;

/**
 * @author purgeyao
 * @since 1.0
 */
public class RadosTest {
    public static void main(String[] args) throws RadosException {
        //获取句柄

        Rados cluster = new Rados("admin");

        System.out.println("Created cluster handle.");

        File f = new File("/etc/ceph/ceph.conf");

        //读取配置文件

        cluster.confReadFile(f);

        System.out.println("Read the configuration file.");

        //连接集群

        cluster.connect();

        System.out.println("Connected to the cluster.");
    }
}
