package omega.lang.zk.registor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import omega.lang.common.utils.PreconditionUtil;
import omega.lang.zk.commons.ZKUtils;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

public class RegistService {

	private String zookeeperIps = "47.93.226.99:2181,47.93.226.99:2182,47.93.226.99:2183";
	private ZkClient zkClient =null;
	private static final String slash = "/";
	private String registRootPath = "/omega/serviceList";
	private Random random = new Random();
	private ConcurrentHashMap<String, List<ServiceNode>> serviceMap = new ConcurrentHashMap<String, List<ServiceNode>>();
	
	private IZkChildListener listner = new IZkChildListener() {

		@Override
		public void handleChildChange(String parentPath,
				List<String> currentChilds) throws Exception {
			System.out.println(String
					.format("[ZookeeperRegistry] service list change: path=%s, currentChilds=%s",
							parentPath, currentChilds.toString()));
			initServiceMap(parentPath, currentChilds);
		}
	};

	public void init(String zookeeperIps) {
		this.zookeeperIps = zookeeperIps;
		zkClient = new ZkClient(zookeeperIps, 60000, 1000);
		if (!zkClient.exists(registRootPath)) {
			zkClient.createPersistent(registRootPath, true);
		}
		
	}

	public void doRegist(String serviceId, String serviceIp, String servicePort) {

		// 条件校验
		boolean hasEmpty = StringUtils.isEmpty(serviceIp)
				|| StringUtils.isEmpty(serviceIp)
				|| StringUtils.isEmpty(servicePort);
		PreconditionUtil.checkState(!hasEmpty, "服务注册入参校验失败");

		String serviceNode = registRootPath + slash + serviceId ;
		
	 
		
		if(!zkClient.exists(serviceNode)){
			zkClient.createPersistent(serviceNode, true);

		}
		
		System.out.println("sub : " + serviceNode);
		 zkClient.subscribeChildChanges(serviceNode, listner);
		
		String tempNode = serviceNode + slash + serviceIp + ":" + servicePort;
	 

		ZKUtils.createEphemeralNode(tempNode, JSON.toJSONString(ServiceNode
				.generate(serviceIp, servicePort, serviceId)), zkClient);
	}

	public synchronized void doSubscribe() {

		List<String> serviceList = zkClient.getChildren(registRootPath);
		serviceMap.clear();

		for (String serviceNode : serviceList) {
			String node = registRootPath + "/" + serviceNode;
			initServiceMap(node, zkClient.getChildren(node));
		}



	}

	private void initServiceMap(String parentPath, List<String> childrenNodeList) {
		
		List<ServiceNode> nodeList = serviceMap.get(parentPath);
		if(!CollectionUtils.isEmpty(nodeList)){
			nodeList.clear();
		}
		
		if (!CollectionUtils.isEmpty(childrenNodeList)) {
			for (String serviceNodePath : childrenNodeList) {
				try {
					String node = zkClient.readData(parentPath + "/"
							+ serviceNodePath);
					if (!StringUtils.isEmpty(node)) {
						ServiceNode serviceNode = JSON.parseObject(node, ServiceNode.class);
						if (CollectionUtils.isEmpty(nodeList)) {
							nodeList = new ArrayList<ServiceNode>();
						}
						nodeList.add(serviceNode);
						serviceMap.put(serviceNode.getServiceId(), nodeList);
						System.out.println(nodeList.size());
					}
				} catch (Exception e) {
				}
			}
		}

	}

	public ServiceNode loadBalance(String serviceId) {
		List<ServiceNode> nodeList = serviceMap.get(serviceId);
		if (!CollectionUtils.isEmpty(nodeList)) {
			return nodeList.get(random.nextInt(nodeList.size()));
		}
		return null;
	}

	public static void main(String[] args) throws InterruptedException {
		RegistService regist = new RegistService();
		regist.init("47.93.226.99:2181,47.93.226.99:2182,47.93.226.99:2183");
		regist.doSubscribe();
		regist.doRegist("abc", "122.11.1.1", "8080");
		regist.doRegist("abc", "122.11.1.1", "8081");
		regist.doRegist("abc", "122.11.1.1", "8082");
		regist.doRegist("abc", "122.11.1.1", "8083");
		Thread.sleep(1000);
		ServiceNode node = regist.loadBalance("abc");
		System.out.println(JSON.toJSONString(node));
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static class ServiceNode {
		private String ip;
		private String port;
		private String serviceId;

		public static ServiceNode generate(String ip, String port,
				String serviceId) {
			ServiceNode node = new ServiceNode();
			node.ip = ip;
			node.port = port;
			node.serviceId = serviceId;
			return node;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}

		public String getServiceId() {
			return serviceId;
		}

		public void setServiceId(String serviceId) {
			this.serviceId = serviceId;
		}
	}

}
