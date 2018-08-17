package com.example.siamese.zookeeper;

import com.example.siamese.common.URL;

import java.util.List;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/14
 */
public interface ZookeeperClient {

    void create(String path, boolean ephemeral);

    void delete(String path);

    List<String> getChildren(String path);

    List<String> addChildListener(String path, ChildListener listener);

    void removeChildListener(String path, ChildListener listener);

    void addStateListener(StateListener listener);

    void removeStateListener(StateListener listener);

    boolean isConnected();

    void close();

    URL getUrl();
}
