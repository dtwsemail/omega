package omega.user.dal.dao.impl;

import java.util.List;

import omega.lang.utils.CollectionUtils;
import omega.lang.utils.StringUtils;
import omega.user.dal.dao.UserBaseInfoDao;
import omega.user.dal.mapper.UserBaseInfoMapper;
import omega.user.dal.model.UserBaseInfo;
import omega.user.dal.model.UserBaseInfoExample;

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
    
    
    @Override
    public UserBaseInfo selectByMobile(String mobile){
    	UserBaseInfoExample example = new UserBaseInfoExample();
    	example.createCriteria().andMobileEqualTo(mobile);
    	List<UserBaseInfo> list = userBaseInfoMapper.selectByExample(example);
    	return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }


}
