package omega.user.aspect;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import omega.lang.log.Log;
import omega.lang.redis.RedisAspect;
import omega.lang.redis.RedisMethod;
import omega.lang.redis.RedisUtils;
import omega.lang.redis.SerializeUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CacheAspect {

	private static Log log = Log.getLog(CacheAspect.class);

	private static final ConcurrentHashMap<String, RedisMethod> redisMethodMap = new ConcurrentHashMap<String, RedisMethod>();

	private final static String REDIS_KEY_SPLIT = ":";

	private final static String REDIS_NX_KEY = "LOCK_FOR_NX";

	@Autowired
	private RedisUtils redisUtils;

	// 设置切点：使用xml，在xml中配置
	// "execution(* com.fh.taolijie.dao.mapper.JobPostModelMapper.insert*(..))"
	// +
	// "|| execution(* com.fh.taolijie.dao.mapper.JobPostModelMapper.update*(..))"
	// +
	// "|| execution(* com.fh.taolijie.dao.mapper.JobPostModelMapper.delete*(..))"
	// +
	// "|| execution(* com.fh.taolijie.dao.mapper.JobPostModelMapper.increase*(..))"
	// +
	// "|| execution(* com.fh.taolijie.dao.mapper.JobPostModelMapper.decrease*(..))"
	// +
	// "|| execution(* com.fh.taolijie.dao.mapper.JobPostModelMapper.complaint(..))"
	// +
	// "|| execution(* com.fh.taolijie.dao.mapper.JobPostModelMapper.set*(..))"
	@Pointcut("execution(* omega.user.dal.dao.*.*(..))")
	public void redisEnablePointCut() {
	}

	private String genRedisKey(String keyWord, List<Integer> redisKeyIndexList,
			Object[] objs) {
		StringBuffer sb = new StringBuffer();

		if (!StringUtils.isBlank(keyWord)) {
			sb.append(keyWord).append(REDIS_KEY_SPLIT);
		}

		if (!CollectionUtils.isEmpty(redisKeyIndexList)) {
			for (Integer i : redisKeyIndexList) {
				sb.append(objs[i]).append(REDIS_KEY_SPLIT);
			}
		}

		return sb.toString();
	}

	@Around("redisEnablePointCut()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		Object result = null;
		Object[] args = jp.getArgs();

		String signature = jp.getSignature().toLongString();
		RedisMethod method = redisMethodMap.get(signature);
		if (method == null) {
			// 得到被代理的方法
			Method me = ((MethodSignature) jp.getSignature()).getMethod();
			method = RedisAspect.getRedisMethod(me);
			method.setReidsKey(genRedisKey(method.getRedisKeyWord(),
					method.getKeyIndexList(), args));
			method.setReturnType(me.getReturnType());
			method.setList(me.getReturnType().isAssignableFrom(List.class));
			method.setSignature(signature);
			redisMethodMap.putIfAbsent(signature, method);
		}

		if (method != null) {

			String redisKey = method.getReidsKey();

			switch (method.getOperator()) {
			case DISABLE:
				log.info(method.getSignature() + "缓存失效");
				result = jp.proceed(args);
				redisUtils.del(redisKey);
				break;
			case ENABLE:
				log.info(method.getSignature() + "启用用缓存");

				String value = redisUtils.getStr(redisKey);
				if (!StringUtils.isBlank(value)) {
					result = SerializeUtil.deserialize(value, method.isList(),
							method.getMetadataType());
				} else {

					// if(redisUtils.setStrNx(REDIS_NX_KEY + redisKey, value)){
					result = jp.proceed(args);
					value = SerializeUtil.serialize(result);
					redisUtils.setStr(redisKey, method.getExpire(), value);
					// redisUtils.del(REDIS_NX_KEY + redisKey);
					// }

				}
				break;
			default:
				log.info(method.getSignature() + "不使用缓存");
				result = jp.proceed(args);
			}
		} else {
			result = jp.proceed(args);
		}
		return result;
	}

	protected String genKey(String clazzName, String methodName, Object[] args) {
		StringBuilder sb = new StringBuilder(clazzName);
		sb.append(REDIS_KEY_SPLIT);
		sb.append(methodName);
		sb.append(REDIS_KEY_SPLIT);

		for (Object obj : args) {
			sb.append(obj.toString());
			sb.append(REDIS_KEY_SPLIT);
		}

		return sb.toString();
	}
}
