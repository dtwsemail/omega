package omega.lang.redis;

import java.util.concurrent.CopyOnWriteArraySet;

import omega.lang.log.Log;

import org.apache.commons.collections.CollectionUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPoolUtils {

	private Log logger = Log.getLog(RedisPoolUtils.class);

	
	private CopyOnWriteArraySet<JedisPool> redisPoolSet = new CopyOnWriteArraySet<JedisPool>();
	private int MAX_ACTIVE = 50;

	private int MAX_IDLE = 30;

	private int MAX_WAIT_TIME = 5000;

	private String redisIp = "47.93.226.99";

	private int port = 6379;

	private int timeout = 5000;
	
	private String passwd="HEHE";

	private synchronized void initialPool() {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT_TIME);
			config.setTestOnBorrow(true);
			JedisPool jedisPool = new JedisPool(config, redisIp, port, timeout,passwd);
			redisPoolSet.add(jedisPool);
		} catch (Exception e) {
			logger.error("First create JedisPool error : " + e);
		}
	}

	public Jedis getJedis() {
		if (CollectionUtils.isEmpty(redisPoolSet)) {
			initialPool();
		}

		for (JedisPool pool : redisPoolSet) {

			return pool.getResource();

		}

		return null;
	}
	
}
