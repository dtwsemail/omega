package omega.lang.constants;


public enum EnumSex {
    /** 0-男 */
    MALE("0","男"),
    /** 1-女 */
    FEMALE("1","女"),
    /** 2-保密 */
    SECRET("2","保密");
 
    /** 状态码 **/
    private String code;
    /** 状态描述 **/
    private String description;

    /**
     * 私有构造方法
     * 
     * @param code
     *            编码
     * @param description
     *            描述
     **/
    private EnumSex(String code, String description) {
        this.code = code;
        this.description = description;
    }


    public static EnumSex find(String code) {
        for (EnumSex frs : EnumSex.values()) {
            if (frs.getCode().equals(code)) {
                return frs;
            }
        }
        return null;
        //throw new Exception("错误码", "根据code=" + code+ "获取渠道标示失败");
    }
    /**
     * Getter method for property <tt>code</tt>.
     * 
     * @return property value of code
     **/
    public String getCode() {
        return code;
    }
    /**
     * Getter method for property <tt>description</tt>.
     * 
     * @return property value of description
     **/
    public String getDescription() {
        return description;
    }
}
