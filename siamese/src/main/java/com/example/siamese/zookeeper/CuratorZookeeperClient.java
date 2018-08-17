package com.example.siamese.zookeeper;

import com.example.siamese.common.URL;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.Collections;
import java.util.List;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/14
 */
@Slf4j
public class CuratorZookeeperClient extends AbstractZookeeperClient {

    private CuratorFramework client;

    private URL connectUrl;

    public CuratorZookeeperClient(URL url) {
        this.client = CuratorFrameworkFactory.builder()
                .connectString(url.getUrl())
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .connectionTimeoutMs(5000)
                .build();
        this.connectUrl = url;
        closeFlag = false;
    }

    @Override
    protected void createEphemeral(String path) {
        try {
            this.client.create().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (Exception e) {
            log.error("create ephemeral node -- " + e.getMessage());
        }
    }

    @Override
    protected void createPersistent(String path) {
        try {
            this.client.create().forPath(path);
        } catch (Exception e) {
            log.error("create persistent node -- " + e.getMessage());
        }
    }

    @Override
    public void delete(String path) {
        try {
            this.client.delete().forPath(path);
        } catch (Exception e) {
            log.error("delete node -- " + e.getMessage());
        }
    }

    @Override
    public List<String> getChildren(String path) {
        try {
            return this.client.getChildren().forPath(path);
        } catch (Exception e) {
            log.error("get children node -- " + e.getMessage());
        }

        return Collections.emptyList();
    }

    @Override
    public boolean isConnected() {
        return this.client.getZookeeperClient().isConnected();
    }

    @Override
    public void close() {
        if (!closeFlag) {
            this.client.close();
            closeFlag = true;
        }
    }

    @Override
    public URL getUrl() {
        return this.connectUrl;
    }
}
