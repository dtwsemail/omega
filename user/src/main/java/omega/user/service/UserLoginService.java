package omega.user.service;

import omega.user.dal.model.UserLoginInfo;

/**
 * Created by Administrator on 2017/5/13.
 */
public interface UserLoginService {
    UserLoginInfo login(String loginName, String loginPwd);
}
