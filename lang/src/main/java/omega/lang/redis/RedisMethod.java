package omega.lang.redis;

import java.util.List;

import omega.lang.redis.constants.EnumRedisOperator;

public class RedisMethod {
	
	private String Signature;

	private EnumRedisOperator operator;
	
	private int expire;
	
	private Class metadataType;
	
	private Class returnType;
	
	private boolean isList;
	
	private String redisKeyWord;
	
	private String reidsKey;
	
	private List<Integer> keyIndexList;

	public String getSignature() {
		return Signature;
	}

	public void setSignature(String signature) {
		Signature = signature;
	}

	public EnumRedisOperator getOperator() {
		return operator;
	}

	public void setOperator(EnumRedisOperator operator) {
		this.operator = operator;
	}

	public List<Integer> getKeyIndexList() {
		return keyIndexList;
	}

	public void setKeyIndexList(List<Integer> keyIndexList) {
		this.keyIndexList = keyIndexList;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public String getReidsKey() {
		return reidsKey;
	}

	public void setReidsKey(String reidsKey) {
		this.reidsKey = reidsKey;
	}

	public String getRedisKeyWord() {
		return redisKeyWord;
	}

	public void setRedisKeyWord(String redisKeyWord) {
		this.redisKeyWord = redisKeyWord;
	}

	public Class getMetadataType() {
		return metadataType;
	}

	public void setMetadataType(Class metadataType) {
		this.metadataType = metadataType;
	}

	public Class getReturnType() {
		return returnType;
	}

	public void setReturnType(Class returnType) {
		this.returnType = returnType;
	}

	public boolean isList() {
		return isList;
	}

	public void setList(boolean isList) {
		this.isList = isList;
	}
	
	
}
