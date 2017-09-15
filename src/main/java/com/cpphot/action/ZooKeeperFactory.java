package com.cpphot.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import com.gosun.util.ConfigUtils;

/**
 * zookeeper单例工厂
 * @author caixiaopeng
 *
 */
public class ZooKeeperFactory {
	/**
	 * zookeeper实例
	 */
	private static ZooKeeper zk;
	
	/**
	 * 获取zookeeper实例
	 */
	public static ZooKeeper getZookeeperInstance(String configFilePath,Watcher watcher) {
		if (zk != null)
			return zk;
		// 读取配置文件
		Properties prop=ConfigUtils.loadConfigFile2(configFilePath);
		String zkQuorum = prop.getProperty("zk.quorum");
		int sessionTimeout=Integer.valueOf(prop.getProperty("zk.session.timeout","5000"));
				
		zk = getZookeeperInstance(zkQuorum,sessionTimeout,watcher);
		return zk;
	}
	
	/**
	 * 获取zookeeper实例
	 */
	public static ZooKeeper getZookeeperInstance(String zkQuorum,int sessionTimeout,Watcher watcher) {
		if (zk != null)
			return zk;
				
		try {
			zk = new ZooKeeper(zkQuorum, sessionTimeout, watcher);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return zk;
	}
}
