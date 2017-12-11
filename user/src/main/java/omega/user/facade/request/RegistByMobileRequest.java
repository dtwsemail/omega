package omega.user.facade.request;

import omega.lang.facade.common.BaseRequest;

/**
 * Created by Administrator on 2017/5/14.
 */
public class RegistByMobileRequest extends BaseRequest{

    private String mobile;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

 
}
