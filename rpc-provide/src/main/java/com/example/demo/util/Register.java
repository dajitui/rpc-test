package com.example.demo.util;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author M
 */
@Component
public class Register {

    @Value("${server.port}")
    private String port;

    //类似服务名
    private static final String SERVER_PATH = "/provide";

    private static final String ZK_ADDRESS = "127.0.0.1:2181";

    private static final int ZK_TIMEOUT = 20000;

    private static final String LOCAL_IP = "127.0.0.1";

    /**
     * 注册
     */
    @PostConstruct
    public void register() {
        try {
            ZooKeeper zooKeeper = new ZooKeeper(ZK_ADDRESS, ZK_TIMEOUT, (WatchEvent) -> {
            });
            Stat stat = zooKeeper.exists(SERVER_PATH, false);
            if (stat == null) {
                zooKeeper.create(SERVER_PATH, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            String path = LOCAL_IP + ":" + port;
            System.out.println("注册的端口："+port);
            //创建短暂的可排序的子节点
            zooKeeper.create(SERVER_PATH + "/instance", path.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
