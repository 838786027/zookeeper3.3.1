package com.cpphot.action;

import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public class Main {
	public static void main(String[] args) throws KeeperException, InterruptedException {
		ZooKeeper zk=ZooKeeperFactory.getZookeeperInstance("zookeeper.properties", null);
        List<String> childs=zk.getChildren("/", null);
        System.out.println(childs);
	}
}
