package omega.lang.redis;

import omega.lang.common.utils.SerialUtil;
import redis.clients.jedis.ShardedJedis;

public class RedisLock {

	public static String tryLock(String lockName, long acquireTimeout,
			long lockTimeout) {

		String result = SerialUtil.generateSerialNo();
		long beginTime = System.currentTimeMillis();
		long endTime = beginTime + acquireTimeout;
		ShardedJedis jedisPool = null;
		try {
			while (System.currentTimeMillis() < endTime) {
				jedisPool = RedisUtils.getJedisPool();
				int lockTime = (int) (lockTimeout / 1000);
				long i = jedisPool.setnx(lockName, result);
				if (i == 1) {
					jedisPool.expire(lockName, lockTime);
					return result;
				}

				//判断是否设置过期时间
				if (jedisPool.ttl(lockName) == -1) {
					jedisPool.expire(lockName, lockTime);
				}

				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		} finally {
			if(jedisPool!=null){
				jedisPool.close();
			}
		}
		return result;
	}
	
	
	public void releaseLock(String lockName,String identify){
		ShardedJedis jedisPool = null;
		try {
			jedisPool = RedisUtils.getJedisPool();
			if(identify.equals(jedisPool.get(lockName))){
				jedisPool.del(lockName);
			}
		} finally {
			if(jedisPool!=null){
				jedisPool.close();
			}
		}
	}
}
