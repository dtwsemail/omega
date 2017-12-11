package omega.lang.zk.test;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class TestAnsyCreateNode implements Watcher  {

 
	    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

	    public static void main(String[] args) throws Exception {
	        ZooKeeper zookeeper = new ZooKeeper("47.93.226.99:2181", 5000, new TestAnsyCreateNode());
	        System.out.println(zookeeper.getState());
	        connectedSemaphore.await();

	        zookeeper.create("/zk-test-ephemeral-", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
	                new IStringCallback(), "I am context. ");

	        zookeeper.create("/zk-test-ephemeral-", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
	                new IStringCallback(), "I am context. ");

	        zookeeper.create("/zk-test-ephemeral-", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,
	                new IStringCallback(), "I am context. ");
	        Thread.sleep(5000);
	        zookeeper.close();
	    }

	    public void process(WatchedEvent event) {
	        if (KeeperState.SyncConnected == event.getState()) {
	            connectedSemaphore.countDown();
	        }
	    }
	}

	class IStringCallback implements AsyncCallback.StringCallback {
	    public void processResult(int rc, String path, Object ctx, String name) {
	        System.out.println("Create path result: [" + rc + ", " + path + ", " + ctx + ", real path name: " + name);
	    }
}
