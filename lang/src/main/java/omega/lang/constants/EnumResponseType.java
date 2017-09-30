package omega.lang.constants;

/**
 * Created by Administrator on 2017/5/14.
 */
public enum EnumResponseType {

    SUCCESS("SUCCESS", "成功"),
    FAIL("FAIL ", "失败"),
    TIMEOUT("TIMEOUT", "超时");

    /**
     * 状态码
     **/
    private String code;
    /**
     * 状态描述
     **/
    private String description;


    private EnumResponseType(String code, String description) {
        this.code = code;
        this.description = description;
    }


    public static EnumResponseType find(String code) {
        for (EnumResponseType frs : EnumResponseType.values()) {
            if (frs.getCode().equals(code)) {
                return frs;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
