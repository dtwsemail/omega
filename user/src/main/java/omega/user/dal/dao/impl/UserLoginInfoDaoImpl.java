package omega.user.dal.dao.impl;

import omega.lang.redis.annotation.RedisCache;
import omega.lang.redis.annotation.RedisKey;
import omega.lang.redis.constants.EnumRedisOperator;
import omega.user.dal.dao.UserLoginInfoDao;
import omega.user.dal.mapper.UserLoginInfoMapper;
import omega.user.dal.model.UserBaseInfo;
import omega.user.dal.model.UserLoginInfo;
import omega.user.dal.model.UserLoginInfoExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/5/13.
 */

@Service
public class UserLoginInfoDaoImpl implements UserLoginInfoDao {

    @Autowired
    private UserLoginInfoMapper userLoginInfoMapper;


    @Override
    public int insert(UserLoginInfo info){
        return info==null ? 0 : userLoginInfoMapper.insert(info);
    }


    @Override
    @RedisCache(operator=EnumRedisOperator.ENABLE, redisKeyWord="userLoginInfo",metadataType=UserLoginInfo.class)
    public UserLoginInfo selectByLoginNameAndPwd(@RedisKey String loginName,@RedisKey  String loginPwd){
        UserLoginInfoExample example = new UserLoginInfoExample();
        example.createCriteria().andLoginNameEqualTo(loginName).andLoginPwdEqualTo(loginPwd);
        List<UserLoginInfo> result = userLoginInfoMapper.selectByExample(example);
        return CollectionUtils.isEmpty(result) ? null : result.get(0);
    }



}
