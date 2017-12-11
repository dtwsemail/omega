package omega.lang.common.utils;

import omega.lang.log.Log;

public class BeanUtils {

	private final static Log log = Log.getLog(BeanUtils.class);
	
	public static void copyProperties(Object dest , Object orig){
		try {
		  org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			log.error("复制对象失败",e);
		};
	}
}
