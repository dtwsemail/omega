package omega.lang;

import omega.lang.constants.EnumErrorCode;

/**
 * Created by Administrator on 2017/5/13.
 */
public class BizException extends RuntimeException {

    private EnumErrorCode errorCode;
    private  String errorMsg;

    public BizException(EnumErrorCode errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

}
