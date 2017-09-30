package omega.user.service.impl;

import omega.lang.constants.EnumErrorCode;
import omega.lang.constants.EnumUserLoginStatus;
import omega.lang.utils.DateUtil;
import omega.lang.utils.PreconditionUtil;
import omega.lang.utils.SerialUtil;
import omega.user.dal.dao.UserBaseInfoDao;
import omega.user.dal.dao.UserLoginInfoDao;
import omega.user.dal.model.UserBaseInfo;
import omega.user.dal.model.UserLoginInfo;
import omega.user.service.UserRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/5/13.
 */
@Service
public class UserRegistServiceImpl implements UserRegistService {

    @Autowired
    private UserBaseInfoDao userBaseInfoDao;

    @Autowired
    private UserLoginInfoDao userLoginInfoDao;


    @Override
    @Transactional
    public void registByMobile(String mobile, String pwd ) {

        UserBaseInfo userBaseInfo = addUserBaseInfo(mobile);
        addUserLoginInfo(mobile, pwd, userBaseInfo);
    }


    private void addUserLoginInfo(String mobile, String pwd, UserBaseInfo userBaseInfo) {
        UserLoginInfo info = new UserLoginInfo();
        info.setId(SerialUtil.generateSerialNo());
        info.setUserNo(userBaseInfo.getId());
        info.setLoginName(mobile);
        info.setLoginPwd(pwd);
        info.setCreateTime(DateUtil.getCurrentTime());
        info.setStatus(EnumUserLoginStatus.NORMAL.getCode());
        int n = userLoginInfoDao.insert(info);
        PreconditionUtil.checkState(n > 0, "添加用户登录信息失败", EnumErrorCode.DB_EXCEPTION);
    }

    private UserBaseInfo addUserBaseInfo(String mobile) {
        UserBaseInfo baseInfo = new UserBaseInfo();
        baseInfo.setMobile(mobile);
        baseInfo.setId(SerialUtil.generateSerialNo());
        baseInfo.setCreateTime(DateUtil.getCurrentTime());
        int n = userBaseInfoDao.insert(baseInfo);
        PreconditionUtil.checkState(n > 0, "新增用户信息失败", EnumErrorCode.DB_EXCEPTION);
        return baseInfo;
    }

}
