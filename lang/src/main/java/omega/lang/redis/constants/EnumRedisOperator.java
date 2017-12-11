package omega.lang.redis.constants;

public enum EnumRedisOperator {

	ENABLE("ENABLE", "使用"), DISABLE("DISABLE", "失效"), NULL("NULL", "不使用");

	private String code;
	private String description;

	EnumRedisOperator(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
