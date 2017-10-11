
package omega.lang.constants;


public enum EnumErrorCode {
    UNEXPECTED_EXCEPTION("030000", "系统预期外异常"),
    DB_EXCEPTION("030001", "数据库操作异常"),
    SYS_RECORD_EXCEPTION("030002", "系统数据记录异常"),
    VALIDATE_ERROR_EXCEPTION("030003","校验失败");


    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误描述
     */
    private String errorDesc;

    private EnumErrorCode(String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

}
