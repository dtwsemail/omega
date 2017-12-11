package omega.lang.zk.commons;

import java.util.List;

import omega.lang.common.utils.PreconditionUtil;

import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;

public class ZKUtils {

	
   public static List<String> analysisZkPath(String path) {
        boolean b = Strings.isNullOrEmpty(path);
        if (b) {
            throw new NullPointerException("path is null !");
        }
        List<String> list = Splitter.on("/").omitEmptyStrings().trimResults().splitToList(path);
        return list;
    }
	   
   
   
    public static String getPath(List<String> pathList){
    	StringBuffer sb = new StringBuffer();
    	for(String s : pathList){
    		sb.append("/").append(s);
    	}
    	return sb.toString();
    }
   
    
    
	public static void createEphemeralNode(String path , Object data, ZkClient client){
		
		PreconditionUtil.checkState(!StringUtils.isEmpty(path), "节点路径不能为空");
		
		if(client.exists(path)){
			System.out.println("dete node : " + path);
			client.delete(path);
		}
		
		List<String> pathList = analysisZkPath( path);
		String parentPath = getPath(pathList.subList(0, pathList.size()-1));
		
		if(!client.exists(parentPath)){
			System.out.println("create node : " + parentPath);
			client.createPersistent(parentPath,true);
		}
		
		String nodePath = getPath(pathList);
		
		System.out.println("create node : " + nodePath);
		client.createEphemeral(nodePath, data);
	}
}
