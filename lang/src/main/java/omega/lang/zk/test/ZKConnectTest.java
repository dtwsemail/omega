package omega.lang.zk.test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ZKConnectTest implements Watcher {

	    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

	    @Override
	    public void process(WatchedEvent event) {
	        System.out.println("Receive watched event : " + event);
	        if (KeeperState.SyncConnected == event.getState()) {
	            connectedSemaphore.countDown();
	        }
	    }

	    public static void main(String[] args) throws IOException {
	        ZooKeeper zookeeper = new ZooKeeper("47.93.226.99:2181", 5000, new ZKConnectTest());
	        System.out.println(zookeeper.getState());
	        try {
	            connectedSemaphore.await();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        System.out.println("Zookeeper session established");
	    }
}
