package omega.lang.redis;

import com.alibaba.fastjson.JSON;

public class SerializeUtil {

	public static String serialize(Object target) {
		return JSON.toJSONString(target);
	}

	public static Object deserialize(String jsonString, boolean isList,
			Class clazz) {

		// 序列化结果是普通对象
		return isList ? JSON.parseArray(jsonString, clazz) : JSON.parseObject(
				jsonString, clazz);
	}

}
