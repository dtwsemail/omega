package omega.user.service.impl;


import omega.lang.common.utils.PreconditionUtil;
import omega.user.dal.dao.UserLoginInfoDao;
import omega.user.dal.model.UserLoginInfo;
import omega.user.service.UserLoginService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/13.
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserLoginInfoDao userLoginInfoDao;

    @Override
    public UserLoginInfo login(String loginName, String loginPwd) {
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(loginPwd)) {
            return null;
        }

        UserLoginInfo loginInfo = userLoginInfoDao.selectByLoginNameAndPwd(loginName, loginPwd);
        
        PreconditionUtil.checkNotNull(loginInfo, "用户名或密码错误");
        return loginInfo;
    }

}
