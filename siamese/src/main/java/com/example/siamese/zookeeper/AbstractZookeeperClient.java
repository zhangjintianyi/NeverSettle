package com.example.siamese.zookeeper;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/14
 */
public abstract class AbstractZookeeperClient implements ZookeeperClient {

    protected static final Set<StateListener> stateListeners = new CopyOnWriteArraySet<StateListener>();

    protected volatile boolean closeFlag = true;

    @Override
    public void create(String path, boolean ephemeral) {
        int i = -1;
        if ((i = path.lastIndexOf('/')) > 0) {
            // 前面创建永久节点，后面创建临时节点
            create(path.substring(0, i), false);
        }
        if (ephemeral) {
            createEphemeral(path);
        } else {
            createPersistent(path);
        }
    }

    /**
     * 创建临时节点
     * @param path
     */
    protected abstract void createEphemeral(String path);

    /**
     * 创建永久节点
     * @param path
     */
    protected abstract void createPersistent(String path);

    @Override
    public List<String> addChildListener(String path, ChildListener listener) {
        return null;
    }

    @Override
    public void removeChildListener(String path, ChildListener listener) {

    }

    @Override
    public void addStateListener(StateListener listener) {
        stateListeners.add(listener);
    }

    @Override
    public void removeStateListener(StateListener listener) {
        stateListeners.remove(listener);
    }
}
