package omega.user.facade.pojo.request;

import omega.lang.facade.common.Request;

/**
 * Created by Administrator on 2017/5/14.
 */
public class RegistByMobile extends Request{

    private String moblie;

    private String password;

    private String nickName;


    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
