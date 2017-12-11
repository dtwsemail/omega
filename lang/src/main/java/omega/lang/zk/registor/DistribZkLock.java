package omega.lang.zk.registor;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import omega.lang.common.utils.PreconditionUtil;
import omega.lang.zk.commons.ZKLockException;
import omega.lang.zk.commons.ZKUtils;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class DistribZkLock {

	private String zookeeperIps = "47.93.226.99:2181,47.93.226.99:2182,47.93.226.99:2183";

	private ZkClient zkClient = new ZkClient(zookeeperIps);
	
	private static final String TOTOL_LOCK_ROOT = "/omega/lock";
	

//	private CopyOnWriteArraySet<String> lockSet = new CopyOnWriteArraySet<String>();
//	private ConcurrentHashMap<String,String> lockMap = new ConcurrentHashMap<String,String>();
	
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
	
	private Object lock = new Object();
	
	private IZkChildListener listner = new IZkChildListener() {
		
		@Override
		public void handleChildChange(String parentPath, List<String> currentChilds)
				throws Exception {
			System.out.println("parentPath" + parentPath+ "current child" + currentChilds.toString());
			lock.notifyAll();
		}
	};
	
	
	public boolean tryLock(String path , long waitTime){
		
		
		PreconditionUtil.checkState(!StringUtils.isEmpty(path), "路径不能为空");
		
		long startTime = System.currentTimeMillis();
		
		path = TOTOL_LOCK_ROOT +  path ;
		
		List<String> pathList = ZKUtils.analysisZkPath(path);
		
		String parentPath =ZKUtils.getPath(pathList.subList(0, pathList.size()-1)) ;
		
		if(!zkClient.exists(parentPath)){
			zkClient.createPersistent(parentPath,true);
		}
 		
		final String node = zkClient.createEphemeralSequential(path, StringUtils.EMPTY);
		System.out.println(node);
		zkClient.subscribeChildChanges(parentPath, listner);
		
		executor.schedule(new Runnable() {
			
			@Override
			public void run() {
				if(zkClient.exists(node)){
					zkClient.delete(node);
				}
			}
		}, 10, TimeUnit.SECONDS);
	
		synchronized (lock) {
			while(true){
				
				try {
					if(isMinNode(parentPath, node)){
						System.out.println("i am get lock " + node);
						return true;
					}
					
					long currentTime = System.currentTimeMillis();
					if(currentTime-startTime>waitTime){
						System.out.println("i am empire");
						break;
					}
					System.out.println("i want to waiting");
					lock.wait(500);
					System.out.println("i wake up!!");
				} catch ( Exception e) {
					System.out.println("lock fail");
					break;
				}
			}
		}
		
	

		return false;
	}
	
	public void unLock(String path){
		zkClient.delete(path);
	}
	
	private boolean isMinNode(String parentPath , String currentNode) throws ZKLockException{
		
		List<String> childrenPathList  = zkClient.getChildren(parentPath);
		Collections.sort(childrenPathList);
		System.out.println("current waiting list : " + childrenPathList.toString());
		String node = currentNode.substring(parentPath.length() + 1);
		int index = childrenPathList.indexOf(node);
		switch(index){
		case -1 : 
			//节点不存在
			throw new ZKLockException();
		case 0 : 
			return true;
		}
		
		return false;
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {
		DistribZkLock lock = new DistribZkLock();
		boolean flag = lock.tryLock("/test", 5000);
		lock.unLock("/test");
		boolean flag1 = lock.tryLock("/test", 30000);

		System.out.println(flag);
	}
}
