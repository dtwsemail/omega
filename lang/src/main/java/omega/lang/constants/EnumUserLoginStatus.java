package omega.lang.constants;

/**
 * Created by Administrator on 2017/5/13.
 */
public enum EnumUserLoginStatus {

    NORMAL("0", "正常"),
    FROZEN("1", "冻结");
    /**
     * 状态码
     **/
    private String code;
    /**
     * 状态描述
     **/
    private String description;


    private EnumUserLoginStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static EnumUserLoginStatus find(String code) {
        for (EnumUserLoginStatus frs : EnumUserLoginStatus.values()) {
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
