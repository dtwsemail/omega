package omega.user.dal.dao.impl;

import omega.user.dal.dao.UserBaseInfoDao;
import omega.user.dal.mapper.UserBaseInfoMapper;
import omega.user.dal.model.UserBaseInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/13.
        */


@Service
public class UserBaseInfoDaoImpl implements UserBaseInfoDao {

    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;

    @Override
    public int insert(UserBaseInfo info) {
        return info == null ? 0 : userBaseInfoMapper.insert(info);
    }

    @Override
    public UserBaseInfo selectByUserId(String userId) {
        return StringUtils.isBlank(userId) ? null : userBaseInfoMapper.selectByPrimaryKey(userId);
    }


}
