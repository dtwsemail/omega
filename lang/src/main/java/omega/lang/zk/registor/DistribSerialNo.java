package omega.lang.zk.registor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import omega.lang.common.utils.PreconditionUtil;
import omega.lang.zk.commons.ZKUtils;

import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;

public class DistribSerialNo {
	
	private String zookeeperIps = "47.93.226.99:2181,47.93.226.99:2182,47.93.226.99:2183";

	private ZkClient zkClient = new ZkClient(zookeeperIps);
	
	private static final String TOTOL_SERIAL_ROOT = "/omega/serial";
	
	private ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
	
	public String genDistribSerialNo(String path){
		
		PreconditionUtil.checkState(!StringUtils.isEmpty(path), "路径不能为空");
		 final String nodePath = TOTOL_SERIAL_ROOT + path;
		
		List<String> pathList = ZKUtils.analysisZkPath(nodePath);
		
		String parentPath  = ZKUtils.getPath(pathList.subList(0, pathList.size()-1));
		if(!zkClient.exists(parentPath)){
			zkClient.createPersistent(parentPath, true);
		}
		
		String serialNode = zkClient.createEphemeralSequential(nodePath, StringUtils.EMPTY);
		System.out.println(serialNode);
		int index = serialNode.lastIndexOf("/");
		
		service.schedule(new Runnable(){

			@Override
			public void run() {
				 if(zkClient.exists(nodePath)){
					 zkClient.delete(nodePath);
				 }
			}
			
		}, 10, TimeUnit.SECONDS);
		return serialNode.substring(index+1);
	}
	
	public static void main(String[] args) {
		DistribSerialNo s = new DistribSerialNo();
		
		String s1 = s.genDistribSerialNo("/2/1");
		System.out.println(s1);
	}

}
