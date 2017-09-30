package omega.user.dal.dao;

import omega.user.dal.model.UserLoginInfo;

/**
 * Created by Administrator on 2017/5/13.
 */
public interface UserLoginInfoDao {
    int insert(UserLoginInfo info);

    UserLoginInfo selectByLoginNameAndPwd(String loginName, String loginPwd);
}
