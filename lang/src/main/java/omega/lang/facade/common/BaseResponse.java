package omega.lang.facade.common;

import omega.lang.constants.EnumResponseType;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

    private String errorMsg;

    private EnumResponseType responseType;

    private T returnObj;


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public EnumResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(EnumResponseType responseType) {
        this.responseType = responseType;
    }

    public T getReturnObj() {
        return returnObj;
    }

    public void setReturnObj(T returnObj) {
        this.returnObj = returnObj;
    }

    @Override
    public String toString() {
        return "Response{" +
                "errorMsg='" + errorMsg + '\'' +
                ", responseType=" + responseType +
                ", returnObj=" + returnObj +
                '}';
    }
}
