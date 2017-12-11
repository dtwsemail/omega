package omega.lang.redis;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import omega.lang.redis.annotation.RedisCache;
import omega.lang.redis.annotation.RedisKey;
import omega.lang.redis.constants.EnumRedisOperator;

public class RedisAspect {

	public static EnumRedisOperator getRedisOperator(Method me) {
		RedisCache annotation = me.getAnnotation(RedisCache.class);
		return annotation == null ? EnumRedisOperator.NULL : annotation
				.operator();
	}

	public static List<Integer> getRedisKeyIndex(Method me) {

		List<Integer> result = new ArrayList<Integer>();
		int index = 0;
		Annotation[][] paramAnnoList = me.getParameterAnnotations();
		if (paramAnnoList != null) {
			for (Annotation[] annos : paramAnnoList) {
				if (annos != null) {
					for (Annotation anno : annos) {
						if (RedisKey.class == anno.getClass()) {
							result.add(Integer.valueOf(index));
						}
					}
				}
				index++;
			}
		}
		return result;
	}

	
	public static RedisMethod getRedisMethod(Method me) {
		RedisMethod result = new RedisMethod();
		RedisCache annotation = me.getAnnotation(RedisCache.class);
		EnumRedisOperator operator = annotation == null ? EnumRedisOperator.NULL
				: annotation.operator();
		
		switch (operator) {
		case ENABLE:
		case DISABLE:
			result.setOperator(operator);
			result.setKeyIndexList(getRedisKeyIndex(me));
			result.setExpire(annotation.expire());
			result.setRedisKeyWord(annotation.redisKeyWord());
			result.setMetadataType(annotation.metadataType());
			break;
		case NULL:
			result.setOperator(operator);
			result.setKeyIndexList(Collections.EMPTY_LIST);
			break;
		}
		return result;
	}

}
