package omega.lang.zk.test;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class TestDeleteNode  implements Watcher {
 

	    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
	    private static ZooKeeper zk;

	    public static void main(String[] args) throws Exception {
	        String path = "/zk-book";
	        zk = new ZooKeeper("47.93.226.99:2181", 5000,
	                new TestDeleteNode());
	        connectedSemaphore.await();

	        zk.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	        System.out.println("success create znode: " + path);
	        zk.create(path + "/c1", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	        System.out.println("success create znode: " + path + "/c1");
	        try {
	            zk.delete(path, -1);
//	            异步删除节点
//	            zk.delete(path, -1, new IVoidCallback(), null);    
	        } catch (Exception e) {
	            System.out.println("fail to delete znode: " + path);
	        }
	        
	        zk.delete(path + "/c1", -1);
	        System.out.println("success delete znode: " + path + "/c1");
	        zk.delete(path, -1);        
	        System.out.println("success delete znode: " + path);

	        Thread.sleep(Integer.MAX_VALUE);
	    }

	    public void process(WatchedEvent event) {
	        if (KeeperState.SyncConnected == event.getState()) {
	            if (EventType.None == event.getType() && null == event.getPath()) {
	                connectedSemaphore.countDown();
	            }
	        }
	    }
	    
//	    class IVoidCallback implements AsyncCallback.VoidCallback {
//	        public void processResult(int rc, String path, Object ctx) {
//	            System.out.println(rc + ", " + path + ", " + ctx);
//	        }
//	    }
}
