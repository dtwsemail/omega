package omega.lang.redis;

import java.util.Set;

import omega.lang.log.Log;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisUtils {

	private Log logger = Log.getLog(RedisUtils.class);

	private   volatile static ShardedJedisPool shardedJedisPool;

	public static ShardedJedis getJedisPool() {
		return shardedJedisPool.getResource();
	}

	public void returnRedisPool(ShardedJedis jedis) {
		shardedJedisPool.returnResource(jedis);
	}
	
	public void returnBrokenRedisPool(ShardedJedis jedis){
		shardedJedisPool.returnBrokenResource(jedis);;
	}

	public void setStr(String key, String value) {
		ShardedJedis jedisPool = null;
		try {
			jedisPool = getJedisPool();
			jedisPool.set(key,value);
			returnRedisPool(jedisPool);
		} catch (Exception e) {
			returnBrokenRedisPool(jedisPool);
			logger.error("Set key error : " + e);
		}
	}

	/**
	 * 设置 过期时间
	 * 
	 * @param key
	 * @param seconds
	 *            以秒为单位
	 * @param value
	 */
	public void setStr(String key, int seconds, String value) {
		ShardedJedis jedisPool = null;
		try {
			jedisPool = getJedisPool();
			if(seconds>0){
				jedisPool.setex(key, seconds,  value);
			}else{
				jedisPool.set(key, value);
			}
			
			returnRedisPool(jedisPool);
		} catch (Exception e) {
			returnBrokenRedisPool(jedisPool);
			logger.error("Set keyex error : " + e);
		}
	}

	/**
	 * 获取String值
	 * 
	 * @param key
	 * @return value
	 */
	public String getStr(String key) {
		String result = null;
		ShardedJedis jedisPool = null;
		try {
			jedisPool = getJedisPool();
			result = jedisPool.get(key);
			returnRedisPool(jedisPool);
		} catch (Exception e) {
			returnBrokenRedisPool(jedisPool);
			logger.error("get str error : " + e);
		}
		return result;
	}
	
	public boolean setStrNx(String key ,String value){
		Long result = 0l;
		ShardedJedis jedisPool = null;
		try {
			jedisPool = getJedisPool();
			result = jedisPool.setnx(key, value) ;
			returnRedisPool(jedisPool);
		} catch (Exception e) {
			returnBrokenRedisPool(jedisPool);
			logger.error("get str error : " + e);
		}
		return result==1;
	}
	
	public void del(String key) {
		ShardedJedis jedisPool = null;
		try {
			jedisPool = getJedisPool();
			 jedisPool.del(key);
			returnRedisPool(jedisPool);
		} catch (Exception e) {
			returnBrokenRedisPool(jedisPool);
			logger.error("get str error : " + e);
		}
	}

	public void lpush(String key, String value) {
		getJedisPool().lpush(key, value);
	}

	public String lpop(String key) {
		return getJedisPool().lpop(key);
	}

	public void sadd(String key, String value) {
		getJedisPool().sadd(key, value);
	}

	public Set<String> smembers(String key) {
		return getJedisPool().smembers(key);
	}

	public void zadd(String key, double score, String member) {
		getJedisPool().zadd(key, score, member);
	}

	public Set<String> zrange(String key, long start, long end) {
		return getJedisPool().zrange(key, start, end);
	}

	public void hput(String key, String field, String value) {
		getJedisPool().hset(key, field, value);
	}

	public String hget(String key, String field) {
		return getJedisPool().hget(key, field);
	}

	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	public static void main(String[] args) {
		RedisUtils utils = new RedisUtils();
		utils.sadd("11A", "a");
		utils.sadd("11A", "b");
		utils.sadd("11A", "c");
		System.out.println(utils.smembers("11A"));

	}
}
