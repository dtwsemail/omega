package omega.lang.common.utils;


import omega.lang.BizException;
import omega.lang.constants.EnumErrorCode;

public class PreconditionUtil {


    public static void checkNotNull(Object obj,
                                    EnumErrorCode errorCode) {
        doCheck(obj != null, errorCode.getErrorDesc(), errorCode);
    }

    

    public static void checkNotNull(Object obj,
                                    String errorMsg ) {
      doCheck(obj != null, errorMsg, EnumErrorCode.UNEXPECTED_EXCEPTION);
    }
    public static void checkNotNull(Object obj,
                                    String errorMsg, EnumErrorCode errorCode) {
        doCheck(obj != null, errorMsg, errorCode);
    }

    public static void checkArgument(boolean expression,
                                       EnumErrorCode errorCode) {
        doCheck(expression, errorCode.getErrorDesc(), errorCode);
    }
    public static void checkArgument(boolean expression,
                                     String errorMsg, EnumErrorCode errorCode) {
        doCheck(expression, errorMsg, errorCode);
    }

    public static void checkState(boolean expression,
                                  EnumErrorCode errorCode) {
        doCheck(expression, errorCode.getErrorDesc(), errorCode);
    }

    
    public static void checkState(boolean expression,
            String errorMsg ) {
			doCheck(expression, errorMsg, EnumErrorCode.UNEXPECTED_EXCEPTION);
	}
    
    public static void checkState(boolean expression,
                                  String errorMsg, EnumErrorCode errorCode) {
        doCheck(expression, errorMsg, errorCode);
    }

    public static void throwException(String errorMsg, EnumErrorCode errorCode) {
        doCheck(false, errorMsg, errorCode);
    }

    private static void doCheck(boolean expression,
                                String errorMsg, EnumErrorCode errorCode) {
        if (!expression) {
            throw new BizException(errorCode, errorMsg);
        }
    }
}
